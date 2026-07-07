<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.User" %>
<%
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        padding-top: 50px;
    }

    .card {
        background: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.15);
        padding: 30px 40px;
        width: 400px;
    }

    h1 {
        color: #333;
        margin-bottom: 5px;
    }

    h2 {
        color: #555;
        font-weight: normal;
        margin-top: 0;
    }

    hr {
        border: none;
        border-top: 1px solid #ddd;
        margin: 20px 0;
    }

    .details p {
        margin: 8px 0;
        color: #333;
    }

    .details strong {
        display: inline-block;
        width: 100px;
    }

    .logout {
        display: inline-block;
        margin-top: 20px;
        padding: 8px 16px;
        background-color: #e74c3c;
        color: #fff;
        text-decoration: none;
        border-radius: 5px;
    }

    .logout:hover {
        background-color: #c0392b;
    }
</style>
</head>
<body>

<div class="card">
    <h1>Welcome to FoodApp</h1>
    <h2>Hello <%= user.getName() %></h2>

    <hr>

    <div class="details">
        <p><strong>User ID:</strong> <%= user.getUserId() %></p>
        <p><strong>Name:</strong> <%= user.getName() %></p>
        <p><strong>Username:</strong> <%= user.getUsername() %></p>
        <p><strong>Email:</strong> <%= user.getEmail() %></p>
        <p><strong>Phone:</strong> <%= user.getPhone() %></p>
        <p><strong>Address:</strong> <%= user.getAddress() %></p>
        <p><strong>Role:</strong> <%= user.getRole() %></p>
    </div>
	<a href="restaurants" style="display:inline-block; margin-bottom:15px; padding:8px 16px; background:#333; color:#fff; text-decoration:none; border-radius:5px;">Browse Restaurants</a>
    <a class="logout" href="logout">Logout</a>
    <a href="cart" style="display:inline-block; margin-bottom:15px; margin-left:10px; padding:8px 16px; background:#e74c3c; color:#fff; text-decoration:none; border-radius:5px;">View Cart</a>
    <a href="orders" style="display:inline-block; margin-bottom:15px; margin-left:10px; padding:8px 16px; background:#e74c3c; color:#fff; text-decoration:none; border-radius:5px;">My Orders</a>
</div>

</body>
</html>