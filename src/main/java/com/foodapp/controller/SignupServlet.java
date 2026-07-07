package com.foodapp.controller;

import java.io.IOException;

import com.foodapp.dao.impl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * SignupServlet
 * This servlet handles user registration.
 */

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Read values from the signup form

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Step 2: Create User object

        User user = new User();

        // Step 3: Store form values into User object

        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole("Customer");

        // Step 4: Create DAO object

        UserDAOImpl dao = new UserDAOImpl();

        // Step 5: Insert user into database

        int result = dao.addUser(user);

        // Step 6: Check whether insertion was successful

        if(result > 0) {

            // Redirect to login page

            response.sendRedirect(request.getPathInfo()+"/login.jsp");

        } else {

            response.getWriter().println("Signup Failed");

        }

    }

}
