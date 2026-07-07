<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Order" %>
<%@ page import="com.foodapp.model.OrderItemDetail" %>
<%@ page import="com.foodapp.dao.impl.OrderDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 30px;
    }

    h1 {
        text-align: center;
        color: #333;
    }

    .order-card {
        max-width: 600px;
        margin: 20px auto;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        padding: 20px;
    }

    .order-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 1px solid #eee;
        padding-bottom: 10px;
        margin-bottom: 10px;
    }

    .order-header h3 {
        margin: 0;
        color: #333;
    }

    .status {
        padding: 4px 10px;
        border-radius: 15px;
        font-size: 13px;
        font-weight: bold;
        color: #fff;
    }

    .status-pending { background-color: #f39c12; }
    .status-preparing { background-color: #3498db; }
    .status-delivered { background-color: #2ecc71; }

    .order-item-row {
        display: flex;
        align-items: center;
        padding: 8px 0;
    }

    .order-item-row img {
        width: 50px;
        height: 50px;
        object-fit: cover;
        border-radius: 6px;
        margin-right: 12px;
    }

    .order-item-row .name {
        flex: 1;
        color: #333;
    }

    .order-item-row .qty {
        color: #888;
        margin-right: 15px;
    }

    .order-item-row .price {
        font-weight: bold;
        color: #e74c3c;
    }

    .order-total {
        text-align: right;
        margin-top: 10px;
        font-weight: bold;
        color: #333;
        border-top: 1px solid #eee;
        padding-top: 10px;
    }

    .no-orders {
        text-align: center;
        color: #888;
        margin-top: 50px;
    }

    .no-orders a {
        color: #e74c3c;
        text-decoration: none;
    }
</style>
</head>
<body>

<h1>My Orders</h1>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    OrderDAOImpl orderDao = (OrderDAOImpl) request.getAttribute("orderDao");

    if (orders == null || orders.isEmpty()) {
%>
    <div class="no-orders">
        <p>You haven't placed any orders yet.</p>
        <a href="restaurants">Browse Restaurants</a>
    </div>
<%
    } else {
        for (Order order : orders) {

            List<OrderItemDetail> items = orderDao.getOrderItemsByOrderId(order.getOrderId());

            String statusClass = "status-pending";
            if ("Preparing".equalsIgnoreCase(order.getStatus())) {
                statusClass = "status-preparing";
            } else if ("Delivered".equalsIgnoreCase(order.getStatus())) {
                statusClass = "status-delivered";
            }
%>
    <div class="order-card">
        <div class="order-header">
            <h3>Order #<%= order.getOrderId() %></h3>
            <span class="status <%= statusClass %>"><%= order.getStatus() %></span>
        </div>

        <p style="color:#888; font-size:13px; margin-top:-5px;"><%= order.getOrderDate() %></p>

<%
        for (OrderItemDetail item : items) {
%>
        <div class="order-item-row">
            <img src="<%= item.getImageUrl() %>" alt="<%= item.getItemName() %>"
                 onerror="this.src='https://via.placeholder.com/50?text=No+Image';">
            <span class="name"><%= item.getItemName() %></span>
            <span class="qty">x<%= item.getQuantity() %></span>
            <span class="price">₹<%= item.getSubtotal() %></span>
        </div>
<%
        }
%>

        <div class="order-total">Total: ₹<%= order.getTotalAmount() %></div>
    </div>
<%
        }
    }
%>

</body>
</html>