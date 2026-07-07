package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.User;

/*
 * UserDAO interface contains all CRUD methods
 * that will be implemented in UserDAOImpl.
 */
public interface UserDAO {

    // Insert a new user
    int addUser(User user);

    // Fetch a user using user id
    User getUserById(int userId);

    // Fetch a user using username
    User getUserByUsernameAndPassword(String username,String password);

    // Fetch all users
    List<User> getAllUsers();

    // Update user details
    int updateUser(User user);

    // Delete a user
    int deleteUser(int userId);

}