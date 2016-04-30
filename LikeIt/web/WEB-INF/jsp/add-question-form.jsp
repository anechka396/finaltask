<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 28.04.2016
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../../js/topic-ajax.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="loc"/>
    <fmt:message bundle="${loc}" key="prop.question.add" var="addQuestion"/>
    <fmt:message bundle="${loc}" key="prop.question.type" var="typeQuestion"/>
</head>
<body>
<div class="container">
    <div class="col-xs-offset-1 col-xs-10" style="background-color: rgba(255, 255, 255, 0.5); padding: 10px">
        <form action="Controller" method="post" class="form">
            <input type="hidden" name="command" value="add-question">
            <div class="form-group">
                <textarea rows="5" class="form-control" placeholder="${typeQuestion}" name="text" required><c:out value="${param.text}"/></textarea>
            </div>
            <div class="form-group">
                <select id="ajaxGetUserServletResponse" class="form-control" name="topic" required>

                </select>
            </div>
            <input type="submit" value="${addQuestion}" class="btn btn-primary">
        </form>
    </div>
</div>
</body>
</html>
