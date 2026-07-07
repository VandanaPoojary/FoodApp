package com.foodapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.DBUtil.DBConnection;
import com.foodapp.dao.MenuDAO;
import com.foodapp.model.MenuItem;

public class MenuDAOImpl implements MenuDAO {

    private Connection connection;

    public MenuDAOImpl() {
        connection = DBConnection.getConnection();
    }

    private final String GET_MENU_BY_RESTAURANT_ID =
            "SELECT * FROM menu WHERE restaurant_id=? AND is_available=1";

    private final String GET_MENU_ITEM_BY_ID =
            "SELECT * FROM menu WHERE menu_id=?";

    @Override
    public List<MenuItem> getMenuByRestaurantId(int restaurantId) {

        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        try {

            PreparedStatement ps = connection.prepareStatement(GET_MENU_BY_RESTAURANT_ID);
            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                MenuItem item = new MenuItem();

                item.setMenuId(rs.getInt("menu_id"));
                item.setRestaurantId(rs.getInt("restaurant_id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setAvailable(rs.getBoolean("is_available"));
                item.setImageUrl(rs.getString("image_url"));

                menuItems.add(item);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    @Override
    public MenuItem getMenuItemById(int menuId) {

        MenuItem item = null;

        try {

            PreparedStatement ps = connection.prepareStatement(GET_MENU_ITEM_BY_ID);
            ps.setInt(1, menuId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                item = new MenuItem();

                item.setMenuId(rs.getInt("menu_id"));
                item.setRestaurantId(rs.getInt("restaurant_id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setAvailable(rs.getBoolean("is_available"));
                item.setImageUrl(rs.getString("image_url"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }
}