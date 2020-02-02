<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <style>
        table, td {
            border: 1px solid #0e4864;
            border-collapse: collapse;
            padding: 10px;
        }
    </style>
</head>
<body>
<table>
    <caption>Users</caption>
    <thead>
    <tr>
        <td></td>
        <td>Username</td>
        <td>Password</td>
        <td>Activity</td>
        <td>Age</td>
        <td>Address</td>
        <td>Telephone</td>
    </tr>
    </thead>
    <form action="${pageContext.request.contextPath}/users/delete" method="post">
        <c:forEach var="user" items="${users}" varStatus="count">
        <tr>
            <td>
                <input type="checkbox" name="id${count.index}" value="${user.id}">
            </td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td>
                <c:choose>
                    <c:when test="${user.active}">
                        <c:out value="${trueValue}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${falseValue}"/>
                    </c:otherwise>
                </c:choose></td>
            <td><c:out value="${user.age}"/></td>
            <td><c:out value="${user.address}"/></td>
            <td><c:out value="${user.telephone}"/></td>
        </tr>
        </c:forEach>
</table>
<br>
<input type="submit" value="Delete checked users">
<input type="submit" formaction="${pageContext.request.contextPath}/users/add" value="Add user">
</form>
</body>
</html>
