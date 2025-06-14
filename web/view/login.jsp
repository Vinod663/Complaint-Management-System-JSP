<%--
  Created by IntelliJ IDEA.
  User: Vinod Niloshana
  Date: 6/14/2025
  Time: 5:24 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    <label>Username:</label><input type="text" name="username" required><br>
    <label>Password:</label><input type="password" name="password" required><br>
    <button type="submit">Login</button>
    <button type="button">Sign Up</button>
</form>
<p style="color:red;">
    ${error}
</p>
</body>
</html>

