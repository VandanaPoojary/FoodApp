<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurants</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 30px; }
    h1 { text-align: center; color: #333; }
    .grid { display: flex; flex-wrap: wrap; gap: 20px; justify-content: center; margin-top: 30px; }
    .card { background: #fff; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); width: 250px; padding: 15px; text-align: center; overflow: hidden; }
    .card img { width: 100%; height: 150px; object-fit: cover; border-radius: 8px; background-color: #ddd; }
    .card h3 { margin: 10px 0 5px; color: #333; }
    .card p { margin: 3px 0; color: #666; font-size: 14px; }
    .card a { display: inline-block; margin-top: 10px; padding: 8px 16px; background-color: #e74c3c; color: #fff; text-decoration: none; border-radius: 5px; }
    .card a:hover { background-color: #c0392b; }
</style>
</head>
<body>

<h1>Restaurants</h1>

<div class="grid">
<%
    List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
    for (Restaurant r : restaurants) {
%>
    	<div class="card">
    	<img src="<%= r.getImageUrl() %>" alt="<%= r.getName() %>"
         	style="width:100%; height:150px; object-fit:cover; border-radius:8px;"
         	onerror="this.src='https://via.placeholder.com/250x150?text=No+Image';">
    	<h3><%= r.getName() %></h3>
        <p><%= r.getCuisineType() %></p>
        <p><%= r.getAddress() %></p>
        <p>⭐ <%= r.getRating() %></p>
        <a href="menu?restaurant_id=<%= r.getRestaurantId() %>">View Menu</a>
    </div>
<%
    }
%>
</div>

</body>
</html>