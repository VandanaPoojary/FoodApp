<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.foodapp.model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
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

    .cart-container {
        max-width: 700px;
        margin: 30px auto;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        padding: 20px;
    }

    .cart-item {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 15px 0;
        border-bottom: 1px solid #eee;
    }

    .cart-item img {
        width: 70px;
        height: 70px;
        object-fit: cover;
        border-radius: 8px;
        margin-right: 15px;
    }

    .item-details {
        display: flex;
        align-items: center;
        flex: 1;
    }

    .item-info h4 {
        margin: 0 0 5px 0;
        color: #333;
    }

    .item-info p {
        margin: 0;
        color: #888;
        font-size: 14px;
    }

    .quantity-controls {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .quantity-controls a {
        background-color: #e74c3c;
        color: #fff;
        text-decoration: none;
        width: 25px;
        height: 25px;
        border-radius: 5px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-weight: bold;
    }

    .subtotal {
        font-weight: bold;
        color: #e74c3c;
        width: 80px;
        text-align: right;
    }

    .remove-link {
        margin-left: 15px;
        color: #999;
        text-decoration: none;
        font-size: 13px;
    }

    .remove-link:hover {
        color: #e74c3c;
    }

    .total-row {
        display: flex;
        justify-content: space-between;
        padding-top: 20px;
        font-size: 18px;
        font-weight: bold;
        color: #333;
    }

    .checkout-btn {
        display: block;
        width: 100%;
        margin-top: 20px;
        padding: 12px;
        background-color: #e74c3c;
        color: #fff;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        text-align: center;
        text-decoration: none;
        cursor: pointer;
    }

    .checkout-btn:hover {
        background-color: #c0392b;
    }

    .empty-cart {
        text-align: center;
        color: #888;
        padding: 40px 0;
    }

    .continue-link {
        display: inline-block;
        margin-top: 15px;
        color: #e74c3c;
        text-decoration: none;
    }
</style>
</head>
<body>

<h1>Your Cart</h1>

<div class="cart-container">

<%
    Map<Integer, CartItem> cart = (Map<Integer, CartItem>) request.getAttribute("cart");
    Double total = (Double) request.getAttribute("total");

    if (cart == null || cart.isEmpty()) {
%>
    <div class="empty-cart">
        <p>Your cart is empty.</p>
        <a class="continue-link" href="restaurants">Browse Restaurants</a>
    </div>
<%
    } else {
        for (CartItem item : cart.values()) {
%>
    <div class="cart-item">
        <div class="item-details">
            <img src="<%= item.getImageUrl() %>" alt="<%= item.getItemName() %>"
                 onerror="this.src='https://via.placeholder.com/70?text=No+Image';">
            <div class="item-info">
                <h4><%= item.getItemName() %></h4>
                <p>₹<%= item.getPrice() %> each</p>
            </div>
        </div>

        <div class="quantity-controls">
            <a href="cart?action=decrease&menuId=<%= item.getMenuId() %>">-</a>
            <span><%= item.getQuantity() %></span>
            <a href="cart?action=increase&menuId=<%= item.getMenuId() %>">+</a>
        </div>

        <div class="subtotal">₹<%= item.getSubtotal() %></div>

        <a class="remove-link" href="cart?action=remove&menuId=<%= item.getMenuId() %>">Remove</a>
    </div>
<%
        }
%>
    <div class="total-row">
        <span>Total:</span>
        <span>₹<%= total %></span>
    </div>

    <a class="checkout-btn" href="checkout">Proceed to Checkout</a>
    <a class="continue-link" href="restaurants" style="display:block; text-align:center; margin-top:15px;">← Add More Items</a>
<%
    }
%>

</div>

</body>
</html>