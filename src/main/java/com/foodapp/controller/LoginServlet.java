package com.foodapp.controller;

import java.io.IOException;

import com.foodapp.dao.impl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * LoginServlet
 * This servlet handles user login.
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Step 1: Read username and password from login form

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// FIX: convert username to lowercase so "Rahul" matches "rahul" in DB
		username = username.toLowerCase();

		System.out.println("Username = [" + username + "]");
		System.out.println("Password = [" + password + "]");

		// Step 2: Create DAO object

		UserDAOImpl dao = new UserDAOImpl();

		// Step 3: Get user by username and password

		User user = dao.getUserByUsernameAndPassword(username, password);

		System.out.println("User = " + user);

		if (user != null) {
			System.out.println("DB Password = " + user.getPassword());
		}

		// Step 4: Check user exists and password matches

		if (user != null && user.getPassword().equals(password)) {

			// Step 5: Create session

			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", user);

			// Step 6: Redirect to home page

			response.sendRedirect(request.getContextPath() + "/home.jsp");

		} else {

			// Step 7: Login failed

			response.setContentType("text/html");
			response.getWriter().println("Invalid Username or Password");

		}
	}
}