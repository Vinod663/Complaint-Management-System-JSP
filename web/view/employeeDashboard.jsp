<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.cms.model.User, com.cms.model.Complaint" %>
<%@ page session="true" %>

<%
    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null || !"employee".equals(currentUser.getRole())) {
        response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        return;
    }

    List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/dashboard.css">
    <style>
        .logout-btn {
            position: absolute;
            top: 20px;
            right: 20px;
        }
    </style>
</head>
<body>
<div class="dashboard-container">
    <div class="logout-btn">
        <form action="<%= request.getContextPath() %>/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>

    <h1>Welcome, <%= currentUser.getUsername() %>!</h1>
    <h2>Employee Dashboard</h2>

    <!-- Complaint Submission Form -->
    <h3>Submit New Complaint</h3>
    <form action="<%= request.getContextPath() %>/submit-complaint" method="post">
        <label>Title:</label><br>
        <input type="text" name="title" required><br><br>

        <label>Description:</label><br>
        <textarea name="description" rows="4" cols="50" required></textarea><br><br>

        <button type="submit">Submit Complaint</button>
    </form>

    <hr>

    <!-- List of My Complaints -->
    <h3>My Complaints</h3>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Remark</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>
        <%
            if (complaints != null && !complaints.isEmpty()) {
                for (Complaint c : complaints) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getTitle() %></td>
            <td><%= c.getDescription() %></td>
            <td><%= c.getStatus() %></td>
            <td><%= c.getRemark() == null ? "-" : c.getRemark() %></td>
            <td><%= c.getDate() %></td>
            <td>
                <% if (!"Resolved".equals(c.getStatus())) { %>
                <form action="<%= request.getContextPath() %>/edit-complaint" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="<%= c.getId() %>">
                    <button type="submit">Edit</button>
                </form>
                <form action="<%= request.getContextPath() %>/employee-dashboard" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= c.getId() %>">
                    <button type="submit" onclick="return confirm('Are you sure?');">Delete</button>
                </form>
                <% } else { %>
                <em>Locked</em>
                <% } %>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="7">No complaints found.</td>
        </tr>
        <% } %>
    </table>
</div>
<script src="<%= request.getContextPath() %>/assets/jquery-3.7.1.min.js"></script>
<script src="<%= request.getContextPath() %>/js/employeeDashboard.js"></script>
</body>
</html>
