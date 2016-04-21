<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Login Page</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="prop" var="lang"/>
    <fmt:message bundle="${lang}" key="prop.send" var="send"/>
</head>
<body>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="login">
    <input type="text" name="login" value="">
    <input type="password" name="password" value="">
    <input type="submit" value="${send}">
</form>
</body>
</html>
