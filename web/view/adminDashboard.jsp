<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.cms.model.Complaint" %>
<%@ page session="true" %>
<%
    List<Complaint> allComplaints = (List<Complaint>) request.getAttribute("allComplaints");
    String message = (String) request.getAttribute("message");
%>
<html>
<head>
    <title>Admin Dashboard - Complaint Management System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/dashboard.css">
    <style>
        .logout-btn {
            float: right;
            margin-top: -60px;
        }
        .logout-btn form {
            display: inline;
        }
    </style>
</head>
<body>
<div class="dashboard-container">
    <h1>Welcome Admin</h1>

    <div class="logout-btn">
        <form action="<%= request.getContextPath() %>/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>

    <h2>All Complaints</h2>

    <% if (message != null) { %>
    <p style="color: green;"><%= message %></p>
    <% } %>

    <table border="1" cellpadding="10">
        <thead>
        <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Remark</th>
            <th>Date Created</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <% if (allComplaints != null && !allComplaints.isEmpty()) {
            for (Complaint c : allComplaints) { %>
        <tr>
            <form action="<%= request.getContextPath() %>/update-complaint-from-admin" method="post">
                <td><%= c.getId() %><input type="hidden" name="id" value="<%= c.getId() %>"/></td>
                <td><%= c.getUserId() %></td>
                <td><%= c.getTitle() %></td>
                <td><%= c.getDescription() %></td>
                <td>
                    <select name="status">
                        <option value="Pending" <%= c.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                        <option value="In Progress" <%= c.getStatus().equals("In Progress") ? "selected" : "" %>>In Progress</option>
                        <option value="Resolved" <%= c.getStatus().equals("Resolved") ? "selected" : "" %>>Resolved</option>
                    </select>
                </td>
                <td><input type="text" name="remark" value="<%= c.getRemark() == null ? "" : c.getRemark() %>"/></td>
                <td><%= c.getDate() %></td>
                <td><button type="submit">Update</button></td>
            </form>
            <form action="<%= request.getContextPath() %>/delete-complaint" method="post">
                <input type="hidden" name="id" value="<%= c.getId() %>"/>
                <td><button type="submit" onclick="return confirm('Are you sure you want to delete this complaint?');">Delete</button></td>
            </form>
        </tr>
        <%  }
        } else { %>
        <tr><td colspan="9">No complaints found.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
