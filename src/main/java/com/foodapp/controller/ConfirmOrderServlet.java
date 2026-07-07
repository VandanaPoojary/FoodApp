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

@WebServlet("/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get current session
        HttpSession session = request.getSession();

        // Get logged in user
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get cart
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        // Get address and payment mode
        String deliveryAddress = request.getParameter("address");
        String paymentMode = request.getParameter("paymentMode");

        // Calculate total amount
        double totalAmount = 0;

        for (CartItem item : cart.values()) {
            totalAmount += item.getSubtotal();
        }

        // Restaurant ID
        // (For now taking the first item's restaurant)
     // Temporary: using restaurant ID 1
     // Later we'll store the actual restaurant ID in the cart/session.
     int restaurantId = 1;
        // Place order
        OrderDAOImpl orderDAO = new OrderDAOImpl();

        int orderId = orderDAO.placeOrder(
                user.getUserId(),
                restaurantId,
                totalAmount,
                deliveryAddress,
                paymentMode,
                cart);

        if (orderId > 0) {

            // Clear cart
            session.removeAttribute("cart");

            request.setAttribute("orderId", orderId);
            request.setAttribute("total", totalAmount);

            request.getRequestDispatcher("order_success.jsp")
                   .forward(request, response);

        } else {

            response.getWriter().println("Order Failed");

        }
    }
}