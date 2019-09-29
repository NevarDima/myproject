<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 9/29/19
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<a href="${pageContext.request.contextPath}/logout">logout</a>
<h3>Пользователи</h3>
<c:if test="${users != null}">
    <table>
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>phone</th>
            <th>email</th>
        </tr>
        <c:forEach items="${users}" var="student">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<c:if test="${authUser.role == 'ADMINISTRATOR'}">
    <h3>Добавить пользователя</h3>
    <form action="${pageContext.request.contextPath}/user" method="post">
        <label for="firstName">имя</label>
        <input id="firstName" type="text" name="firstName"><br/>

        <label for="lastName">фамилия</label>
        <input id="lastName" type="text" name="lastName"><br/>

        <label for="email">email</label>
        <input id="email" type="text" name="email"><br/>

        <label for="phone">phone</label>
        <input id="phone" type="text" name="phone"><br/>

        <input type="submit">
    </form>
</c:if>
