<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>

<style>

body{
    font-family:Arial;
    background:#f5f5f5;
    margin:0;
    padding:30px;
}

.container{
    width:500px;
    margin:auto;
    background:white;
    padding:25px;
    border-radius:10px;
    box-shadow:0 0 10px lightgray;
}

h2{
    text-align:center;
    color:#333;
}

label{
    font-weight:bold;
}

input,
textarea,
select{
    width:100%;
    padding:10px;
    margin-top:10px;
    margin-bottom:20px;
    border:1px solid #ccc;
    border-radius:5px;
    box-sizing:border-box;
}

button{
    width:100%;
    padding:12px;
    background:#e74c3c;
    color:white;
    border:none;
    font-size:18px;
    cursor:pointer;
    border-radius:5px;
}

button:hover{
    background:#c0392b;
}

</style>

</head>

<body>

<div class="container">

<h2>Checkout</h2>

<form action="confirmOrder" method="post">

<label>Delivery Address</label>

<textarea
name="address"
rows="4"
placeholder="Enter your delivery address"
required></textarea>

<label>Payment Mode</label>

<select name="paymentMode" required>

<option value="">Select Payment Mode</option>

<option value="Cash on Delivery">Cash on Delivery</option>

<option value="UPI">UPI</option>

<option value="Credit Card">Credit Card</option>

<option value="Debit Card">Debit Card</option>

</select>

<button type="submit">

Confirm Order

</button>

</form>

</div>

</body>
</html>