package com.cms.controller;

import com.cms.dao.ComplaintDao;
import com.cms.dao.UserDao;
import com.cms.model.Complaint;
import com.cms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.util.List;

@WebServlet("/employee-dashboard")
public class EmployeeDashboardServlet extends HttpServlet {
    private ComplaintDao complaintDao;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        complaintDao = new ComplaintDao(ds);
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null || !"employee".equals(currentUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        int userId = currentUser.getId();

        List<Complaint> complaints = complaintDao.getComplaintsByUserId(userId);
        req.setAttribute("complaints", complaints);

        req.getRequestDispatcher("/view/employeeDashboard.jsp").forward(req, resp);
    }
}
