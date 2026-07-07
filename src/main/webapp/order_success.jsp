<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Placed</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        padding-top: 80px;
        margin: 0;
    }

    .card {
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.15);
        padding: 40px;
        width: 400px;
        text-align: center;
    }

    .checkmark {
        font-size: 50px;
        color: #2ecc71;
        margin-bottom: 10px;
    }

    h2 {
        color: #333;
        margin-bottom: 10px;
    }

    p {
        color: #666;
        margin: 5px 0;
    }

    .total {
        font-weight: bold;
        color: #e74c3c;
        font-size: 18px;
        margin: 15px 0;
    }

    a {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #e74c3c;
        color: #fff;
        text-decoration: none;
        border-radius: 5px;
    }

    a:hover {
        background-color: #c0392b;
    }
</style>
</head>
<body>

<div class="card">
    <div class="checkmark">✔</div>
    <h2>Order Placed Successfully!</h2>
    <p>Your Order ID: #<%= request.getAttribute("orderId") %></p>
    <p class="total">Total: ₹<%= request.getAttribute("total") %></p>
    <a href="home.jsp">Back to Home</a>
</div>

</body>
</html>