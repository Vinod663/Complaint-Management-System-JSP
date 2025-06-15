<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cms.model.Complaint" %>
<%
    Complaint complaint = (Complaint) request.getAttribute("complaint");
%>
<html>
<head>
    <title>Edit Complaint</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/editComplaint.css">
</head>
<body>
<h2>Edit Complaint</h2>

<form action="<%= request.getContextPath() %>/update-complaint" method="post">
    <input type="hidden" name="id" value="<%= complaint.getId() %>">

    <label>Title:</label><br>
    <input type="text" name="title" value="<%= complaint.getTitle() %>" required><br><br>

    <label>Description:</label><br>
    <textarea name="description" rows="4" cols="50" required><%= complaint.getDescription() %></textarea><br><br>

    <button type="submit">Update Complaint</button>
</form>

</body>
</html>
