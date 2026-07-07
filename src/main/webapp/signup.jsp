<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        padding-top: 40px;
        margin: 0;
    }

    .card {
        background: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.15);
        padding: 30px 40px;
        width: 380px;
        text-align: center;
    }

    h2 {
        color: #333;
        margin-bottom: 25px;
    }

    label {
        display: block;
        text-align: left;
        margin-bottom: 5px;
        color: #555;
        font-size: 14px;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"],
    input[type="tel"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        font-size: 14px;
    }

    input:focus {
        outline: none;
        border-color: #e74c3c;
    }

    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #e74c3c;
        color: #fff;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        margin-top: 5px;
    }

    input[type="submit"]:hover {
        background-color: #c0392b;
    }

    .login-link {
        margin-top: 20px;
        font-size: 14px;
        color: #555;
    }

    .login-link a {
        color: #e74c3c;
        text-decoration: none;
        font-weight: bold;
    }

    .login-link a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>

<div class="card">
    <h2>Create Account</h2>

    <form action="signup" method="post">

        <label>Full Name</label>
        <input type="text" name="name" required>

        <label>Username</label>
        <input type="text" name="username" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Phone</label>
        <input type="tel" name="phone" required>

        <label>Address</label>
        <input type="text" name="address" required>

        <input type="submit" value="Sign Up">

    </form>

    <div class="login-link">
        Already have an account? <a href="login.jsp">Login Here</a>
    </div>
</div>

</body>
</html>