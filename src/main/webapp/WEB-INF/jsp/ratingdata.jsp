<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User ratings info page</title>
</head>
<body>
    <h1>Recipient '${recipient.username}' rating list</h1>

    <c:if test="${!empty ratings}">
        <table border="1">
            <tr>
                <th>Value</th>
                <th>Sender</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="rating" items="${ratings}">
                <tr>
                    <td>${rating.value}</td>
                    <td>${rating.sender.username}</td>
                    <td><a href="/ratings/remove/${rating.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty ratings}">
        <h4>(empty set)</h4>
    </c:if>
</body>
</html>
