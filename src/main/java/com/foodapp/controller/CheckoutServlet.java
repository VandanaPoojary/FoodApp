package com.foodapp.controller;

import java.io.IOException;
import java.util.Map;

import com.foodapp.dao.impl.OrderDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        // Calculate total
        double total = 0;
        int restaurantId = 0;

        for (CartItem item : cart.values()) {
            total += item.getSubtotal();
        }

        // NOTE: since MenuItem doesn't carry restaurantId into CartItem yet,
        // we default to restaurant_id = 1 for now (we'll improve this next)
        restaurantId = 1;

        OrderDAOImpl orderDao = new OrderDAOImpl();
        int newOrderId = orderDao.placeOrder(user.getUserId(), restaurantId, total, cart);

        // Clear the cart after placing order
        session.removeAttribute("cart");

        request.setAttribute("orderId", newOrderId);
        request.setAttribute("total", total);

        request.getRequestDispatcher("order_success.jsp").forward(request, response);
    }
}