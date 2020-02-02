<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Navigation Menu</h2>
<ul style="list-style-type:none;">
    <li>
        <form action="${pageContext.request.contextPath}/users" method="get">
            <input type="submit" value="Show users">
        </form>
    </li>
    <li>
        <form action="${pageContext.request.contextPath}/users/add" method="post">
            <input type="submit" value="Add user">
        </form>
    </li>
    <li>
        <form action="${pageContext.request.contextPath}/users" method="get">
            <input type="submit" value="Delete users">
        </form>
    </li>
</ul>
</body>
</html>
