<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        padding-top: 60px;
        margin: 0;
    }

    .card {
        background: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.15);
        padding: 30px 40px;
        width: 350px;
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
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 18px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        font-size: 14px;
    }

    input[type="text"]:focus,
    input[type="password"]:focus {
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
    }

    input[type="submit"]:hover {
        background-color: #c0392b;
    }

    .signup-link {
        margin-top: 20px;
        font-size: 14px;
        color: #555;
    }

    .signup-link a {
        color: #e74c3c;
        text-decoration: none;
        font-weight: bold;
    }

    .signup-link a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>

<div class="card">
    <h2>User Login</h2>

    <form action="login" method="post">

        <label>Username</label>
        <input type="text" name="username" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <input type="submit" value="Login">

    </form>

    <div class="signup-link">
        Don't have an account? <a href="signup.jsp">Register Here</a>
    </div>
</div>

</body>
</html>