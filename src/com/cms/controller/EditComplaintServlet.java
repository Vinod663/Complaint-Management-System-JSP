package com.cms.controller;

import com.cms.dao.ComplaintDao;
import com.cms.model.Complaint;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet("/edit-complaint")
public class EditComplaintServlet extends HttpServlet {
    private ComplaintDao complaintDao;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("dataSource");
        complaintDao = new ComplaintDao(ds);
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        int complaintId = Integer.parseInt(request.getParameter("id"));
        Complaint complaint = complaintDao.getComplaintById(complaintId);

        if (complaint != null) {
            request.setAttribute("complaint", complaint);
            request.getRequestDispatcher("/view/editComplaint.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/employee-dashboard?error=notfound");
        }
    }
}
