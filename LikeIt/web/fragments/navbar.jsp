<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="../error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/star-rating.min.css">
    <link rel="stylesheet" href="/css/styles.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/app-ajax.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.login" var="login"/>
    <fmt:message bundle="${localization}" key="prop.logout" var="logout"/>
    <fmt:message bundle="${localization}" key="prop.registration" var="reg"/>
    <fmt:message bundle="${localization}" key="prop.search" var="search"/>
    <fmt:message bundle="${localization}" key="prop.hello" var="hello"/>
    <fmt:message bundle="${localization}" key="prop.topics" var="topics"/>
    <fmt:message bundle="${localization}" key="prop.home" var="home"/>
    <fmt:message bundle="${localization}" key="prop.management.topic" var="mgTopic"/>
    <fmt:message bundle="${localization}" key="prop.management.user" var="mgUser"/>
</head>
<body>

<video autoplay loop muted class="bgvideo" id="bgvideo">
    <source src="/source/bg-max.mp4" type="video/mp4">
</video>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../index.jsp" style="font-size: 40px; font-family: 'Poiret One', cursive;">Like It</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/index.jsp">${home}</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">${topics}
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" id="topics">
                    </ul>
                </li>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <li><a href="/TopicManagement">${mgTopic}</a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                        <li>
                            <a href="/registration.jsp">
                                <span class="glyphicon glyphicon-user"></span>
                                    ${reg}
                            </a>
                        </li>
                        <li>
                            <a href="/login.jsp">
                                <span class="glyphicon glyphicon-log-in"></span>
                                    ${login}
                            </a>
                        </li>
                </c:when>
                <c:otherwise>
                    <li><a href="/UserPage">${hello}, <c:out value="${sessionScope.user.login}"/>!</a></li>
                    <li><form action="Controller" method="post" class="navbar-form navbar-right">
                        <input type="hidden" name="command" value="logout">
                        <button type="submit" class="btn btn-link">
                            <span class="glyphicon glyphicon-log-out"></span>
                                ${logout}
                        </button>
                    </form></li>
                </c:otherwise>
            </c:choose>
            </ul>
        </div>
    </div>
</nav>
</body>