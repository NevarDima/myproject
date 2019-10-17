<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 10/14/19
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Registration form</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="POST">
    <legend>Registration form</legend>

    <label for="login">login:</label>
    <input id="login" type="text" name="login" required>

    <label for="password">password:</label>
    <input id="password" type="password" name="password" required>

    <input type="submit" value="Submit"/>
</form>

</body>
</html>
