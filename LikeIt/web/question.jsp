<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
    <script src="js/question-ajax.js"></script>
</head>
<body>

<script id='IPhotoTmpl' type='text/x-jquery-tmpl'>
    <div class="well well-sm" style="font-size: 0px;">
        '<c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.login == answer.author}">
            <button style="font-size: 14px;" class="close" data-id="${requestScope.question.id}"><span aria-hidden="true">&times;</span></button>
        </c:if>'
        <span style="font-size: 14px;">{{html text}}</span>
    </div>
</script>

<div class="container">
    <div class="col-xs-offset-1 col-xs-10">
        <div class="well well-sm">
            <c:out value="${requestScope.question.text}"/>
        </div>
        <div id="answers">
            <c:forEach items="${requestScope.answers}" var="answer">
                <div class="well well-sm">
                    <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.login == answer.author}">
                        <button style="font-size: 14px;" class="close" data-id="${answer.id}"><span aria-hidden="true">&times;</span></button>
                    </c:if>
                    <c:out value="${answer.text}"/>
                </div>
            </c:forEach>
        </div>
        <div class="form">
            <div class="form-group">
                <textarea id="answer-text" class="form-control" required></textarea>
            </div>
            <div class="form-group">
                <button class="btn btn btn-default" id="new-answer" data-id="${requestScope.question.id}">Добавить</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
