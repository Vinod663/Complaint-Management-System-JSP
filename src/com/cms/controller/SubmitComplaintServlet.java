package com.cms.controller;

import com.cms.dao.ComplaintDao;
import com.cms.model.Complaint;
import com.cms.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Timestamp;

import java.io.IOException;

@WebServlet("/submit-complaint") // URL mapping for the submit complaint servlet
public class SubmitComplaintServlet extends HttpServlet {
    private ComplaintDao complaintDao;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        complaintDao = new ComplaintDao(ds);
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null || !"employee".equals(currentUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Complaint complaint = new Complaint();
        complaint.setUserId(currentUser.getId());
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setStatus("Pending");
        /*complaint.setDate(String.valueOf(new Timestamp(System.currentTimeMillis())));*/

        complaintDao.saveComplaint(complaint);

        // Redirect back to dashboard
        resp.sendRedirect(req.getContextPath() + "/employee-dashboard");
    }
}
