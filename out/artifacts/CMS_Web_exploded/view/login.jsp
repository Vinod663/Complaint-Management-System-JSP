<%--
  Created by IntelliJ IDEA.
  User: Vinod Niloshana
  Date: 6/14/2025
  Time: 5:24 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Complaint Management System</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>
<div>
    <div class="system-title">Complaint Management System</div>
    <div class="login-container">
        <h2>Login</h2>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="button-group">
                <button type="submit">Login</button>
                <button type="button" onclick="window.location.href='<%= request.getContextPath() %>/view/register.jsp'">Sign Up</button>
            </div>
        </form>

        <div class="error-message">
            ${error}
        </div>
    </div>
</div>
</body>
</html>