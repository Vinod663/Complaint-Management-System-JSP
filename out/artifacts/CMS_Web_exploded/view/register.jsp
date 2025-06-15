<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Sign Up - Complaint Management System</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/register.css">
</head>
<body>
<div>
    <div class="system-title">Complaint Management System</div>
    <div class="register-container">
        <h2>Register (Employee Only)</h2>

        <div class="role-badge">Employee Registration</div>

        <form class="register-form" action="<%= request.getContextPath() %>/register-employee" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>

            <!-- Hidden role field to restrict user role to 'Employee' -->
            <input type="hidden" name="role" value="Employee">

            <button type="submit">Sign Up</button>
        </form>

        <div class="divider">
            <span>OR</span>
        </div>

        <!-- Sign In Button -->
        <form class="signin-form" action="<%= request.getContextPath() %>/view/login.jsp" method="get">
            <button type="submit">Already have an account? Sign In</button>
        </form>

        <div class="error-message">
            ${error}
        </div>
    </div>
</div>
</body>
</html>