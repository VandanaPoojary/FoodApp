package com.foodapp.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/foodapp_db";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Vandana@123";

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver Loaded");

            connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected");

        }

        catch (ClassNotFoundException e) {

            System.out.println("Driver Not Found");

            e.printStackTrace();

        }

        catch(SQLException e) {

            System.out.println("Database Connection Failed");

            e.printStackTrace();

        }

        return connection;

    }

}