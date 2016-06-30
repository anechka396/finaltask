<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user eq null}">
    <c:redirect url="/Error"/>
</c:if>

<html>
<head>
    <title>Add question</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/topic-ajax.js"></script>

    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.question.add" var="addQuestion"/>
    <fmt:message bundle="${localization}" key="prop.question.type" var="typeQuestion"/>
</head>
<body>
<div class="container-fluid">
    <c:import url="/fragments/navbar.jsp"/>
    <div class="row main-row">
        <div class="col-xs-offset-1 col-xs-10">
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
    <c:import url="/fragments/footer.jsp"/>
</div>
</body>
</html>
