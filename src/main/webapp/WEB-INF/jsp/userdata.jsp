<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info page</title>
    <%@ include file="custom/headers.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="section">
        <h1>User Details</h1>

        <table class="table">
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
    </div>
</div>
</body>
</html>
