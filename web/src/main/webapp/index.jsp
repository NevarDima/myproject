<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 10/2/19
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <title>myproject!</title>
</head>
<body>
<!-- header -->
<div>
    <h1>Super app!</h1>
</div>

<div>       <!-- content -->
    <div>    <!-- buttons holder -->
        <button onclick="location.href='/login'">Login</button>
        <button onclick="location.href='/user'">List of users</button>
    </div>
</div>
</body>
</html>