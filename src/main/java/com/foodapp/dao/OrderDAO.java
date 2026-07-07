package com.foodapp.dao;

import java.util.List;
import java.util.Map;

import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItemDetail;

public interface OrderDAO {

    int placeOrder(int userId, int restaurantId, double totalAmount, Map<Integer, CartItem> cart);

    List<Order> getOrdersByUserId(int userId);

    List<OrderItemDetail> getOrderItemsByOrderId(int orderId);

}