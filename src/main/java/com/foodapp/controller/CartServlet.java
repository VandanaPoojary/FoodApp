package com.foodapp.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.foodapp.dao.impl.MenuDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Get the cart from session, or create a new empty one
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new LinkedHashMap<Integer, CartItem>();
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            int menuId = Integer.parseInt(request.getParameter("menuId"));

            if (cart.containsKey(menuId)) {

                // Item already in cart, increase quantity
                CartItem existingItem = cart.get(menuId);
                existingItem.setQuantity(existingItem.getQuantity() + 1);

            } else {

                // New item, fetch details from database and add to cart
                MenuDAOImpl menuDao = new MenuDAOImpl();
                MenuItem menuItem = menuDao.getMenuItemById(menuId);

                CartItem cartItem = new CartItem();
                cartItem.setMenuId(menuItem.getMenuId());
                cartItem.setItemName(menuItem.getItemName());
                cartItem.setPrice(menuItem.getPrice());
                cartItem.setQuantity(1);
                cartItem.setImageUrl(menuItem.getImageUrl());

                cart.put(menuId, cartItem);
            }

        } else if ("remove".equals(action)) {

            int menuId = Integer.parseInt(request.getParameter("menuId"));
            cart.remove(menuId);

        } else if ("increase".equals(action)) {

            int menuId = Integer.parseInt(request.getParameter("menuId"));
            if (cart.containsKey(menuId)) {
                CartItem item = cart.get(menuId);
                item.setQuantity(item.getQuantity() + 1);
            }

        } else if ("decrease".equals(action)) {

            int menuId = Integer.parseInt(request.getParameter("menuId"));
            if (cart.containsKey(menuId)) {
                CartItem item = cart.get(menuId);
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    cart.remove(menuId);
                }
            }
        }

        // Save updated cart back to session
        session.setAttribute("cart", cart);

        // Calculate total
        double total = 0;
        for (CartItem item : cart.values()) {
            total += item.getSubtotal();
        }

        request.setAttribute("cart", cart);
        request.setAttribute("total", total);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}