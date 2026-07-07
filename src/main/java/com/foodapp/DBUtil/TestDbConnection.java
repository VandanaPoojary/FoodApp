package com.foodapp.DBUtil;

	import java.sql.Connection;

	public class TestDbConnection {

	    public static void main(String[] args) {

	        Connection con = DBConnection.getConnection();

	        if (con != null) {
	            System.out.println("Database Connected Successfully.");
	        } else {
	            System.out.println("Connection Failed.");
	        }
	    }
	}


