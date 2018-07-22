<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info page</title>
</head>
<body>
<h1>User Details</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.password}</td>
    </tr>
</table>
</body>
</html>
