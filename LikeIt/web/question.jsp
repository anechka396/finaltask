<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 29.04.2016
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/question-ajax.js"></script>
</head>
<body>
<h1>Hello</h1>
<c:out value="${requestScope.question.text}"/>
<div id="answers">
    <c:forEach items="${requestScope.answers}" var="answer">
        <c:out value="${answer.text}"/>
    </c:forEach>
</div>
<div class="form">
    <textarea id="answer-text"></textarea>
    <button id="new-answer" data-id="${requestScope.question.id}"></button>
</div>
</body>
</html>
