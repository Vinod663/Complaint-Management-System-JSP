package com.cms.controller;

import com.cms.dao.ComplaintDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet("/delete-complaint") // URL mapping for the delete complaint servlet
public class DeleteComplaintServlet extends HttpServlet {
    private ComplaintDao complaintDao;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        complaintDao = new ComplaintDao(ds);
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        Object userObj = session != null ? session.getAttribute("user") : null;

        if (userObj == null || !"admin".equals(((com.cms.model.User) userObj).getRole())) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        int complaintId = Integer.parseInt(req.getParameter("id"));
        boolean deleted = complaintDao.deleteComplaintById(complaintId);

        if (deleted) {
            req.getSession().setAttribute("message", "Complaint deleted successfully.");
        } else {
            req.getSession().setAttribute("message", "Failed to delete complaint.");
        }

        resp.sendRedirect(req.getContextPath() + "/admin-dashboard");
    }
}
