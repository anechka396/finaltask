<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${requestScope.question == null}">
    <c:redirect url="Controller?command=get-question&id=${param.id}"/>
</c:if>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/star-rating.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/star-rating.min.js"></script>
    <script src="js/question-ajax.js"></script>
</head>
<body>
<div class="container">
    <div class="col-xs-offset-1 col-xs-10">
        <h2>Вопрос:</h2>
        <div class="well well-sm">
            <c:out value="${requestScope.question.text}"/>
        </div>
        <a class="btn btn-default" href="#answer-text">Ответить</a>

        <h2>Ответы:</h2>
        <div id="answers">
            <c:forEach items="${requestScope.answers}" var="answer">
                <div class="well well-sm">
                    <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.login == answer.author}">
                        <button style="font-size: 14px;" class="close" data-id="${answer.id}"><span aria-hidden="true">&times;</span></button>
                    </c:if>
                    <p>${answer.text}</p>
                    <p>${answer.author}</p>
                    <c:if test="${sessionScope.user != null}">
                        <input data-id="${answer.id}" type="number" class="rating" value="${answer.mark}" step="1" data-size="xs">
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.user != null}">
            <form action="/Controller" class="form">
                <input type="hidden" name="command" value="add-answer">
                <input type="hidden" name="id" value="${requestScope.question.id}">
                <div class="form-group">
                    <textarea id="answer-text" class="form-control" name="text" required></textarea>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn btn-default"/>
                </div>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
