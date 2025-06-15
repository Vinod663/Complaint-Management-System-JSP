package com.cms.controller;

import com.cms.dao.ComplaintDao;
import com.cms.model.Complaint;
import com.cms.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

@WebServlet("/admin-dashboard") // URL mapping for the admin dashboard servlet
public class AdminDashboardServlet extends HttpServlet {
    private ComplaintDao complaintDao;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        complaintDao = new ComplaintDao(ds);
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws java.io.IOException, ServletException {
        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null || !"admin".equals(currentUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        String message = (String) req.getSession().getAttribute("message");
        if (message != null) {
            req.setAttribute("message", message);
            req.getSession().removeAttribute("message");
        }

        List<Complaint> allComplaints = complaintDao.getAllComplaints();
        req.setAttribute("allComplaints", allComplaints);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/adminDashboard.jsp");
        dispatcher.forward(req, resp);
    }
}
