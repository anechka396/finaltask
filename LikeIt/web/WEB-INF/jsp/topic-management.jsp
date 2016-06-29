<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user.role ne 'ADMIN'}">
    <c:redirect url="/Error"/>
</c:if>

<c:if test="${requestScope.topics == null}">
    <c:redirect url="Controller?command=get-topics"/>
</c:if>

<html>
<head>
    <title>Topics management</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
</head>
<body>

<div class="container-fluid">
    <c:import url="/fragments/navbar.jsp"/>
    <div class="row main-row">
        <div class="col-sm-12">
            <ul id="topics2">
                <c:forEach items="${requestScope.topics}" var="topic">
                    <li><c:out value="${topic.topic}"/></li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <c:import url="/fragments/footer.jsp"/>
</div>

<script>
    $(document).ready(function() {
        $.fn.editable.defaults.mode = 'inline';
    });
</script>
</body>
</html>
