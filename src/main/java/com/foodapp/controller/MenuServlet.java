package com.foodapp.controller;

import java.io.IOException;
import java.util.List;

import com.foodapp.dao.impl.MenuDAOImpl;
import com.foodapp.dao.impl.RestaurantDAOImpl;
import com.foodapp.model.MenuItem;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId = Integer.parseInt(request.getParameter("restaurant_id"));

        MenuDAOImpl menuDao = new MenuDAOImpl();
        RestaurantDAOImpl restaurantDao = new RestaurantDAOImpl();

        List<MenuItem> menuItems = menuDao.getMenuByRestaurantId(restaurantId);
        Restaurant restaurant = restaurantDao.getRestaurantById(restaurantId);

        request.setAttribute("menuItems", menuItems);
        request.setAttribute("restaurant", restaurant);

        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
}