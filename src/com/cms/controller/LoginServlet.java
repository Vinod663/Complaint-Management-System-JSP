package com.cms.controller;

import com.cms.dao.UserDao;
import com.cms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/login") // URL mapping for the registration servlet
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DataSource dataSource = (DataSource) getServletContext().getAttribute("dataSource");
        UserDao userDao = new UserDao(dataSource);
        User login = userDao.login(username, password);

        if (login != null) {
            request.getSession().setAttribute("user", login);
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_OK);
            response.getWriter().write("Login successful"+
                    "\nWelcome " + login.getUsername() +
                    "\nYour role is " + login.getRole());
            if ("admin".equals(login.getRole())) {
                response.sendRedirect(request.getContextPath() + "/view/adminDashboard.jsp");
            }
            else if ("employee".equals(login.getRole())) {
                response.sendRedirect(request.getContextPath() + "/view/employeeDashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            try {
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
