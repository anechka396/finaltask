<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.user == null}">
    <c:redirect url="/Error"></c:redirect>
</c:if>

<html>
<head>
    <title>Edit user page</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form class="form-horizontal" action="/Controller">
        <input type="hidden" name="command" value="edit-user">
        <div class="form-group">
            <label class="control-label" for="surname">Surname: </label>
            <input class="form-control" type="text" id="surname" name="surname" value="" required>
        </div>
        <div class="form-group">
            <label class="control-label" for="name">Name: </label>
            <input class="form-control" type="text" id="name" name="name" value="${sessionScope.user.name}" required>
        </div>
        <div class="form-group">
            <label class="control-label" for="tel">Tel: </label>
            <input class="form-control" type="text" id="tel" name="tel" value="" required>
        </div>
        <div class="form-group">
            <label class="control-label" for="email">Email: </label>
            <input class="form-control" type="text" id="email" name="email" value="${sessionScope.user.email}" required>
        </div>
        <div class="form-group">
            <input class="btn btn-primary" type="submit" value="Save">
        </div>
    </form>
</div>

</body>
</html>
