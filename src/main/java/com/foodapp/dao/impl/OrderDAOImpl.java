package com.foodapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foodapp.DBUtil.DBConnection;
import com.foodapp.dao.OrderDAO;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItemDetail;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;

    public OrderDAOImpl() {
        connection = DBConnection.getConnection();
    }

    private final String INSERT_ORDER =
            "INSERT INTO orders (user_id, restaurant_id, total_amount, status) VALUES (?, ?, ?, ?)";

    private final String INSERT_ORDER_ITEM =
            "INSERT INTO order_item (order_id, menu_id, quantity, subtotal) VALUES (?, ?, ?, ?)";

    private final String GET_ORDERS_BY_USER_ID =
            "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";

    private final String GET_ORDER_ITEMS_BY_ORDER_ID =
            "SELECT m.item_name, m.image_url, oi.quantity, oi.subtotal " +
            "FROM order_item oi JOIN menu m ON oi.menu_id = m.menu_id " +
            "WHERE oi.order_id = ?";

    @Override
    public int placeOrder(int userId, int restaurantId, double totalAmount, Map<Integer, CartItem> cart) {

        int newOrderId = 0;

        try {

            // Step 1: Insert into orders table
            PreparedStatement orderStmt = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

            orderStmt.setInt(1, userId);
            orderStmt.setInt(2, restaurantId);
            orderStmt.setDouble(3, totalAmount);
            orderStmt.setString(4, "Pending");

            orderStmt.executeUpdate();

            // Step 2: Get the generated order_id
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newOrderId = generatedKeys.getInt(1);
            }

            // Step 3: Insert each cart item into order_item table
            for (CartItem item : cart.values()) {

                PreparedStatement itemStmt = connection.prepareStatement(INSERT_ORDER_ITEM);

                itemStmt.setInt(1, newOrderId);
                itemStmt.setInt(2, item.getMenuId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setDouble(4, item.getSubtotal());

                itemStmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newOrderId;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<Order>();

        try {

            PreparedStatement ps = connection.prepareStatement(GET_ORDERS_BY_USER_ID);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setRestaurantId(rs.getInt("restaurant_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<OrderItemDetail> getOrderItemsByOrderId(int orderId) {

        List<OrderItemDetail> items = new ArrayList<OrderItemDetail>();

        try {

            PreparedStatement ps = connection.prepareStatement(GET_ORDER_ITEMS_BY_ORDER_ID);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderItemDetail item = new OrderItemDetail();

                item.setItemName(rs.getString("item_name"));
                item.setImageUrl(rs.getString("image_url"));
                item.setQuantity(rs.getInt("quantity"));
                item.setSubtotal(rs.getDouble("subtotal"));

                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}