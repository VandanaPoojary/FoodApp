package com.foodapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.DBUtil.DBConnection;
import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

    private Connection connection;

    public RestaurantDAOImpl() {
        connection = DBConnection.getConnection();
    }

    private final String GET_ALL_RESTAURANTS =
            "SELECT * FROM restaurant";

    private final String GET_RESTAURANT_BY_ID =
            "SELECT * FROM restaurant WHERE restaurant_id=?";

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        try {

            PreparedStatement ps = connection.prepareStatement(GET_ALL_RESTAURANTS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCuisineType(rs.getString("cuisine_type"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhone(rs.getString("phone"));
                restaurant.setRating(rs.getDouble("rating"));
                restaurant.setImageUrl(rs.getString("image_url"));

                restaurants.add(restaurant);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {

        Restaurant restaurant = null;

        try {

            PreparedStatement ps = connection.prepareStatement(GET_RESTAURANT_BY_ID);
            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCuisineType(rs.getString("cuisine_type"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhone(rs.getString("phone"));
                restaurant.setRating(rs.getDouble("rating"));
                restaurant.setImageUrl(rs.getString("image_url"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurant;
    }
}