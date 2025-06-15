package com.cms.controller;

import com.cms.dao.UserDao;
import com.cms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet("/register-employee")
public class EmployeeRegisterServlet extends HttpServlet {
    private UserDao userDAO;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        userDAO = new UserDao(ds);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User employee = new User(username, password, "employee");
        try {
            boolean isRegistered = userDAO.registerEmployee(employee);

            if (isRegistered) {
                // Redirect to login/signin page
                resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            } else {
                // Forward back to register form with error
                req.setAttribute("error", "Registration failed. Try again.");
                req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
                System.out.println("Registration failed. Try again.");
            }
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry")) {
                req.setAttribute("error", "Username already exists. Try a different one.");
                System.out.println("Username already exists. Try a different one.");
            } else {
                req.setAttribute("error", "An error occurred. Please try again.");
                e.printStackTrace(); // Log full error for dev
                System.out.println("An error occurred. Please try again.");
            }
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        }
    }
}
