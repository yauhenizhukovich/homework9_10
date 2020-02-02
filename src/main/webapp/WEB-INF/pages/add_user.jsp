<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<form action="${pageContext.request.contextPath}/users/add" method="post">
    <fieldset>
        Username:<input name="username" type="text" maxlength="40" autofocus><br>
        Password:<input name="password" type="password" maxlength="40"><br>
        Activity:<select name="is_active">
        <option value="true">true</option>
        <option value="false" selected>false</option>
    </select>
        <br>
        Age:<input name="age" type="number" maxlength="3"><br>
        Address:<input name="address" type="text" maxlength="100"><br>
        Telephone:<input name="telephone" type="tel" maxlength="15"><br>
        <input type="submit" value="Add">
    </fieldset>
</form>
<c:if test="${validation ne null}">
    <c:out value="${validation}"/>
</c:if>
</body>
</html>
