package com.foodapp.model;

import java.sql.Timestamp;

/*
 * User class represents one record of the user table.
 */
public class User {

    // Variables (same as database columns)

    private int userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private Timestamp createdDate;

    // Default Constructor
    public User() {

    }

    // Parameterized Constructor
    public User(String name, String username, String password,
            String email, String phone, String address, String role) {

        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    // toString() Method

    @Override
    public String toString() {

        return "User [userId=" + userId +
                ", name=" + name +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", phone=" + phone +
                ", address=" + address +
                ", role=" + role +
                ", createdDate=" + createdDate + "]";
    }

}