<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Roles list</h1>

    <c:if test="${!empty roles}">
        <table>
            <tr>
                <th>Id</th>
                <th>Value</th>
            </tr>
            <c:forEach var="role" items="${roles}">
                <tr>
                    <td>${role.id} </td>
                    <td>${role.value}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty roles}">
        <h4>(empty set)</h4>
    </c:if>


    <h1>Users list</h1>

    <c:if test="${!empty users}">
        <table>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Password</th>
                <th>Roles</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td><a href="/userdata/${user.id}">${user.username}</a></td>
                    <td>${user.password}</td>
                    <td>
                        <c:forEach var="role" items="${user.roles}">
                            ${role.value}
                        </c:forEach>
                    </td>
                    <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty users}">
        <h4>(empty set)</h4>
    </c:if>


    <h1>${userAction} user</h1>
    <c:url var="addAction" value="/add"/>

    <form:form action="/add" modelAttribute="user">
        <table>
            <c:if test="${!empty user.username}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>

            <tr>
                <td>
                    <form:label path="username">
                        <spring:message text="Username"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="username"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="password">
                        <spring:message text="Password"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="password"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="rawRoles">
                        <spring:message text="Raw roles"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="rawRoles"/>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <c:if test="${!empty user.username}">
                        <input type="submit"
                               value="<spring:message text="Edit User"/>"/>
                    </c:if>
                    <c:if test="${empty user.username}">
                        <input type="submit"
                               value="<spring:message text="Add User"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>

</body>
</html>
