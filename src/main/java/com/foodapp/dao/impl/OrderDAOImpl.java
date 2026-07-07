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

    // Insert into orders table
    private final String INSERT_ORDER =
            "INSERT INTO orders (user_id, restaurant_id, total_amount, delivery_address, payment_mode, status) VALUES (?, ?, ?, ?, ?, ?)";

    // Insert into order_item table
    private final String INSERT_ORDER_ITEM =
            "INSERT INTO order_item (order_id, menu_id, quantity, subtotal) VALUES (?, ?, ?, ?)";

    // Get all orders of a user
    private final String GET_ORDERS_BY_USER_ID =
            "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";

    // Get all items of an order
    private final String GET_ORDER_ITEMS_BY_ORDER_ID =
            "SELECT m.item_name, m.image_url, oi.quantity, oi.subtotal " +
            "FROM order_item oi JOIN menu m ON oi.menu_id = m.menu_id " +
            "WHERE oi.order_id = ?";

    @Override
    public int placeOrder(int userId,
                          int restaurantId,
                          double totalAmount,
                          String deliveryAddress,
                          String paymentMode,
                          Map<Integer, CartItem> cart) {

        int newOrderId = 0;

        try {

            // Insert order
            PreparedStatement orderStmt = connection.prepareStatement(
                    INSERT_ORDER,
                    Statement.RETURN_GENERATED_KEYS);

            orderStmt.setInt(1, userId);
            orderStmt.setInt(2, restaurantId);
            orderStmt.setDouble(3, totalAmount);
            orderStmt.setString(4, deliveryAddress);
            orderStmt.setString(5, paymentMode);
            orderStmt.setString(6, "Pending");

            orderStmt.executeUpdate();

            // Get generated order id
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                newOrderId = generatedKeys.getInt(1);
            }

            // Insert cart items
            for (CartItem item : cart.values()) {

                PreparedStatement itemStmt =
                        connection.prepareStatement(INSERT_ORDER_ITEM);

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

        List<Order> orders = new ArrayList<>();

        try {

            PreparedStatement ps =
                    connection.prepareStatement(GET_ORDERS_BY_USER_ID);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setRestaurantId(rs.getInt("restaurant_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setPaymentMode(rs.getString("payment_mode"));
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

        List<OrderItemDetail> items = new ArrayList<>();

        try {

            PreparedStatement ps =
                    connection.prepareStatement(GET_ORDER_ITEMS_BY_ORDER_ID);

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