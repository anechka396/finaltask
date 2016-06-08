<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="js/app-ajax.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="loc"/>
    <fmt:message bundle="${loc}" key="prop.login" var="login"/>
    <fmt:message bundle="${loc}" key="prop.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="prop.registration" var="reg"/>
    <fmt:message bundle="${loc}" key="prop.search" var="search"/>
    <fmt:message bundle="${loc}" key="prop.hello" var="hello"/>
    <fmt:message bundle="${loc}" key="prop.topics" var="topics"/>

</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp" style="font-size: 40px; font-family: 'Poiret One', cursive;">Like It</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a data-toggle="collapse" data-target="#topics" id="show-topics">${topics}</a></li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-default">${search}</button>
                    </form>

                    <c:choose>
                        <c:when test="${sessionScope.user == null}">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="registration.jsp">
                                        <span class="glyphicon glyphicon-user"></span>
                                            ${reg}
                                    </a>
                                </li>
                                <li>
                                    <a href="login.jsp">
                                        <span class="glyphicon glyphicon-log-in"></span>
                                            ${login}
                                    </a>
                                </li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <form action="Controller" method="post" class="navbar-form navbar-right">
                                <input type="hidden" name="command" value="logout">
                                <button type="submit" class="btn btn-link">
                                    <span class="glyphicon glyphicon-log-out"></span>
                                        ${logout}
                                </button>
                            </form>
                            <p class="navbar-text navbar-right">${hello}, <a href="/UserPage"><c:out value="${sessionScope.user.login}"/></a>!</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
    </div>
    <div class="col-xs-12 collapse" id="topics">
        <div class="well " style="margin: 0px; background: rgba(255,255,255, 0.5); border: none; border-radius: 0px">
            <div class="btn-group btn-group-justified" id="ajaxGetUserServletResponse">

            </div>
        </div>
    </div>
</div>
</body>