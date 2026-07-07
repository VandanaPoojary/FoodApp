<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Successful</title>

<style>

body{
    font-family:Arial,sans-serif;
    background:#f5f5f5;
    display:flex;
    justify-content:center;
    align-items:center;
    height:100vh;
}

.container{
    background:white;
    padding:40px;
    border-radius:10px;
    box-shadow:0 0 15px rgba(0,0,0,0.2);
    text-align:center;
}

h1{
    color:green;
}

p{
    font-size:18px;
}

a{
    display:inline-block;
    margin-top:20px;
    padding:12px 20px;
    background:#e74c3c;
    color:white;
    text-decoration:none;
    border-radius:5px;
}

a:hover{
    background:#c0392b;
}

</style>

</head>

<body>

<div class="container">

<h1>🎉 Order Placed Successfully!</h1>

<p>Your order has been placed successfully.</p>

<p>Order ID :
<b><%= request.getAttribute("orderId") %></b></p>

<p>Total Amount :
<b>₹<%= request.getAttribute("total") %></b></p>

<a href="restaurants">Continue Shopping</a>

</div>

</body>
</html>