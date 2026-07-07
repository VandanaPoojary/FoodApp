package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.MenuItem;

public interface MenuDAO {

    List<MenuItem> getMenuByRestaurantId(int restaurantId);

    MenuItem getMenuItemById(int menuId);

}