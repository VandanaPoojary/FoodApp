package com.foodapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.DBUtil.DBConnection;
import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;

public class UserDAOImpl implements UserDAO {

    // Connection object
    private Connection connection;

    // Constructor
    public UserDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // SQL Queries

    private final String INSERT_USER =
            "INSERT INTO user(name,username,password,email,phone,address,role) VALUES(?,?,?,?,?,?,?)";

    private final String GET_USER_BY_ID =
            "SELECT * FROM user WHERE user_id=?";

    // FIX: now checks both username AND password
    private final String GET_USER_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM user WHERE username=? AND password=?";

    private final String GET_ALL_USERS =
            "SELECT * FROM user";

    private final String UPDATE_USER =
            "UPDATE user SET name=?,username=?,password=?,email=?,phone=?,address=?,role=? WHERE user_id=?";

    private final String DELETE_USER =
            "DELETE FROM user WHERE user_id=?";

    // Add User
    @Override
    public int addUser(User user) {

        int status = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);

            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Get User By Id
    @Override
    public User getUserById(int userId) {

        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedDate(rs.getTimestamp("created_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // Get User By Username AND Password (used for login)
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {

        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD);

            // trim() avoids failures from accidental leading/trailing spaces from the form
            ps.setString(1, username.trim());
            ps.setString(2, password.trim());

            System.out.println("Searching username = " + username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("User Found");

                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedDate(rs.getTimestamp("created_date"));

            } else {
                System.out.println("User NOT Found");
            }

            // FIX: removed the duplicate second `if (rs.next())` block
            // that was overwriting `user` with the wrong row

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // Get All Users
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<User>();

        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedDate(rs.getTimestamp("created_date"));

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // Update User
    @Override
    public int updateUser(User user) {

        int status = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);

            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());
            ps.setInt(8, user.getUserId());

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Delete User
    @Override
    public int deleteUser(int userId) {

        int status = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_USER);
            ps.setInt(1, userId);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}