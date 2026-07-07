package com.foodapp.controller;

import java.io.IOException;
import java.util.List;

import com.foodapp.dao.impl.OrderDAOImpl;
import com.foodapp.model.Order;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {

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

        OrderDAOImpl orderDao = new OrderDAOImpl();

        List<Order> orders = orderDao.getOrdersByUserId(user.getUserId());

        request.setAttribute("orders", orders);
        request.setAttribute("orderDao", orderDao);

        request.getRequestDispatcher("order_history.jsp").forward(request, response);
    }
}