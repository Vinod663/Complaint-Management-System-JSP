package com.cms.controller;

import com.cms.dao.ComplaintDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet("/update-complaint")
public class UpdateComplaintServlet extends HttpServlet {
    private ComplaintDao complaintDao;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        complaintDao = new ComplaintDao(ds);
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        complaintDao.updateComplaint(id, title, description);
        resp.sendRedirect(req.getContextPath() + "/employee-dashboard");
    }
}
