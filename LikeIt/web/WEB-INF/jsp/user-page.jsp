<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user == null}">
    <c:redirect url="/Error"/>
</c:if>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/styles.css">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
                <img src="../../pictures/no-avatar.png" height="100" width="100">
            </div>
            <div class="col-sm-10">
                <div class="row">
                    <div class="col-sm-2">
                        Имя:
                    </div>
                    <div class="col-sm-10">
                        ${sessionScope.user.name}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        Фамилия:
                    </div>
                    <div class="col-sm-10">
                        ${sessionScope.user.lastName}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        login:
                    </div>
                    <div class="col-sm-10">
                        ${sessionScope.user.login}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        e-mail:
                    </div>
                    <div class="col-sm-10">
                        ${sessionScope.user.email}
                    </div>
                </div>
            </div>
        </div>
    </div>
<a href="../../index.jsp">To main page</a>
<a href="/EditUser">Edit</a>
</body>
</html>
