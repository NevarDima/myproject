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
    <h1>Rentalcars.by</h1>
</div>

<div>       <!-- content -->
    <div>    <!-- buttons holder -->
        <button onclick="location.href='/login'">Login</button>
        <button onclick="location.href='/registration'">Registration</button>
    </div>
</div>
</body>
<h3>Автомобили</h3>
<c:if test="${cars != null}">
    <table>
        <tr>
            <th>Бренд</th>
            <th>Модель</th>
            <th>Тип кузова</th>
            <th>Доступна</th>
        </tr>
        <c:forEach items="${cars}" var="car">
            <tr>
                <td>${car.brend}</td>
                <td>${car.model}</td>
                <td>${car.roleUser}</td>
                <td>${car.blocked}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>