<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 19.04.2016
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="prop" var="lang"/>
    <fmt:message bundle="${lang}" key="prop.send" var="send"/>
</head>
<body>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="registration">
    <input type="text" name="login" value="" placeholder="login">
    <input type="password" name="password" value="" placeholder="password">
    <input type="password" name="repeatPassword" value="" placeholder="repeat password">
    <input type="text" name="name" value="" placeholder="name">
    <input type="email" name="email" value="" placeholder="email">
    <input type="submit" value="${send}">
</form>

</body>
</html>
