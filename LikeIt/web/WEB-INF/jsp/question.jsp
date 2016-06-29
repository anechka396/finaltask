<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${requestScope.question == null}">
    <c:redirect url="/Controller">
        <c:param name="command" value="get-question"/>
        <c:param name="id" value="${param.id}"/>
    </c:redirect>
</c:if>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/star-rating.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/star-rating.min.js"></script>
    <script src="/js/question-ajax.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.question" var="question"/>
    <fmt:message bundle="${localization}" key="prop.reply" var="reply"/>
    <fmt:message bundle="${localization}" key="prop.answers" var="answers"/>
    <fmt:message bundle="${localization}" key="prop.answer.add" var="addAnswer"/>

</head>
<body>
<div class="container-fluid">
    <c:import url="/fragments/navbar.jsp"/>
    <div class="row main-row">
        <div class="col-xs-offset-1 col-xs-10">
            <h2>${question}:</h2>
            <div class="well well-sm">
                <c:choose>
                    <c:when test="${sessionScope.user.login == requestScope.question.author}">
                        <a class="btn" id="edit-question" data-name="questions" data-pk="${requestScope.question.id}" data-url="/Controller"><c:out value="${requestScope.question.text}"/></a>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${requestScope.question.text}"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <a class="btn btn-default" href="#answer-text">${reply}</a>

            <h2>${answers}:</h2>
            <div id="answers">
                <c:forEach items="${requestScope.answers}" var="answer">
                    <div class="well well-sm">
                        <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.login == answer.author}">
                            <button style="font-size: 14px;" class="close" data-id="${answer.id}"><span aria-hidden="true">&times;</span></button>
                        </c:if>
                        <p><c:out value="${answer.text}"/></p>
                        <p><c:out value="${answer.author}"/></p>
                        <c:if test="${sessionScope.user != null}">
                            <input data-id="${answer.id}" type="number" class="rating" value="${answer.mark}" step="1" data-size="xs">
                        </c:if>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${sessionScope.user != null}">
                <form action="/Controller" method="post" class="form">
                    <input type="hidden" name="command" value="add-answer">
                    <input type="hidden" name="id" value="${requestScope.question.id}">
                    <div class="form-group">
                        <textarea id="answer-text" class="form-control" name="text" required></textarea>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn btn-default" value="${addAnswer}"/>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
    <c:import url="/fragments/footer.jsp"/>
</div>
</body>
</html>
