<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.MenuItem" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
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

    .grid {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
        margin-top: 30px;
    }

    .card {
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        width: 250px;
        padding: 15px;
        text-align: center;
    }

    .card h3 {
        margin: 10px 0 5px;
        color: #333;
    }

    .card p {
        margin: 3px 0;
        color: #666;
        font-size: 14px;
    }

    .price {
        font-weight: bold;
        color: #e74c3c;
        margin-top: 8px;
    }

    .card button {
        margin-top: 10px;
        padding: 8px 16px;
        background-color: #e74c3c;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .card button:hover {
        background-color: #c0392b;
    }
</style>
</head>
<body>

<%
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
%>

<h1><%= restaurant.getName() %> - Menu</h1>

<div class="grid">
<%
    List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
    for (MenuItem item : menuItems) {
%>
    	<div class="card">
    	<img src="<%= item.getImageUrl() %>" alt="<%= item.getItemName() %>"
         	style="width:100%; height:150px; object-fit:cover; border-radius:8px;"
         	onerror="this.src='https://via.placeholder.com/250x150?text=No+Image';">
    	<h3><%= item.getItemName() %></h3>
        <p><%= item.getDescription() %></p>
        <p class="price">₹<%= item.getPrice() %></p>
        <a href="cart?action=add&menuId=<%= item.getMenuId() %>"
   style="display:inline-block; margin-top:10px; padding:8px 16px; background-color:#e74c3c; color:#fff; border:none; border-radius:5px; cursor:pointer; text-decoration:none;">
   Add to Cart
</a>
    </div>
<%
    }
%>
</div>

</body>
</html>