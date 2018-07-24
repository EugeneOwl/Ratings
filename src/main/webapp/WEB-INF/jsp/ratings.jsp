<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Rating API</title>
</head>
<body>
    <h5><a href="/users">Go to user API</a></h5>

    <h1>Users list</h1>

    <c:if test="${!empty users}">
        <table>
            <tr>
                <th>Id</th>
                <th>Username</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td><a href="/ratings/user/${user.id}">${user.username}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty users}">
        <h4>(empty set)</h4>
    </c:if>

    <h1>Add rating</h1>

    <form:form action="/ratings/add" modelAttribute="rating">
        <table>
            <tr>
                <td>
                    <form:label path="value">
                        <spring:message text="Value"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="value"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="rawSender">
                        <spring:message text="Sender id"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="rawSender"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="rawRecipient">
                        <spring:message text="Recipient id"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="rawRecipient"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="Add Rating"/>"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
