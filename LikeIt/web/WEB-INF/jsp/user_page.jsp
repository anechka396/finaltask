<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 19.04.2016
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Hello, <c:out value="${sessionScope.login.login}"/> !</h2>
    <a href="../../index.jsp">To main page</a>
</body>
</html>
