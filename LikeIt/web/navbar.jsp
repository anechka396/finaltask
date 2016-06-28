<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/app-ajax.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="loc"/>
    <fmt:message bundle="${loc}" key="prop.login" var="login"/>
    <fmt:message bundle="${loc}" key="prop.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="prop.registration" var="reg"/>
    <fmt:message bundle="${loc}" key="prop.search" var="search"/>
    <fmt:message bundle="${loc}" key="prop.hello" var="hello"/>
    <fmt:message bundle="${loc}" key="prop.topics" var="topics"/>
    <fmt:message bundle="${loc}" key="prop.home" var="home"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp" style="font-size: 40px; font-family: 'Poiret One', cursive;">Like It</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">${home}</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">${topics}
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" id="topics">
                            <li><a href="#">+ Add new topic</a></li>
                    </ul>
                </li>
            </ul>
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
</body>