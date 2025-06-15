package com.cms.controller;

import com.cms.dao.ComplaintDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.commons.dbcp2.BasicDataSource;

@WebServlet("/update-complaint-from-admin") // URL mapping for the update complaint servlet
public class UpdateComplaintFromAdminServlet extends HttpServlet {
    private ComplaintDao complaintDao;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        complaintDao = new ComplaintDao(ds);
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws java.io.IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String status = req.getParameter("status");
            String remark = req.getParameter("remark");

            boolean updated = complaintDao.updateComplaintFromAdmin(id, status, remark);

            if (updated) {
                req.getSession().setAttribute("message", "Complaint updated successfully!");
            } else {
                req.getSession().setAttribute("message", "Failed to update complaint.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("message", "Error while updating complaint.");
        }

        resp.sendRedirect(req.getContextPath() + "/admin-dashboard");
    }
}
