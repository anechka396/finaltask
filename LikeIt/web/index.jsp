<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
  <head>
    <title>Main Page</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="prop" var="lang"/>
    <fmt:message bundle="${lang}" key="prop.login" var="login"/>
    <fmt:message bundle="${lang}" key="prop.logout" var="logout"/>
    <fmt:message bundle="${lang}" key="prop.registration" var="reg"/>
    <fmt:message bundle="${lang}" key="prop.lang.ru" var="ru"/>
    <fmt:message bundle="${lang}" key="prop.lang.en" var="en"/>
    <fmt:message bundle="${lang}" key="prop.search" var="search"/>
    <fmt:message bundle="${lang}" key="prop.question.add" var="addQuestion"/>
    <fmt:message bundle="${lang}" key="prop.question.type" var="typeQuestion"/>
    <fmt:message bundle="${lang}" key="prop.hello" var="hello"/>
  </head>
  <body>

  <video autoplay loop muted class="bgvideo" id="bgvideo">
    <source src="bg-max.mp4" type="video/mp4">
  </video>

  <div class="container-fluid">
    <div class="row">
      <div class="col-xs-12">
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <a class="navbar-brand" href="#">Like It</a>
            </div>

            <div class="collapse navbar-collapse">
              <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                  <input type="text" class="form-control">
                </div>
                <button type="submit" class="btn btn-default">${search}</button>
              </form>

                <c:choose>
                  <c:when test="${sessionScope.login == null}">
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
                    <p class="navbar-text navbar-right">${hello}, <a href="/UserPage"><c:out value="${sessionScope.login.login}"/></a>!</p>
                  </c:otherwise>
                </c:choose>
            </div>
          </div>
        </nav>
        </div>
      </div>


    <div class="row">
      <div class="col-xs-12">
      <div class="col-xs-2 col-sm-4" style="min-height: 100%; background: rgba(255,255,255, 0.5)">
      </div>
      <div class="col-xs-10 col-sm-8" style="background: rgba(255,255,255, 0.5); padding: 10px; min-height: 100%">

        <form action="Controller" method="post" class="form">
          <input type="hidden" name="command" value="add-question">
          <div class="form-group">
            <textarea rows="5" class="form-control" placeholder="${typeQuestion}"></textarea>
          </div>
          <input type="submit" value="${addQuestion}" class="btn btn-primary">
        </form>

        <form action="Controller" method="post" class="lang_button">
          <input type="hidden" name="command" value="locale">
          <input type="hidden" name="locale" value="en">
          <input type="submit" class="btn-link" value="${en}"/>
        </form>

        <form action="Controller" method="post" class="lang_button">
          <input type="hidden" name="command" value="locale">
          <input type="hidden" name="locale" value="ru">
          <input type="submit" class="btn-link"  value="${ru}">
        </form>
      </div>
      </div>
      </div>
    </div>

  </body>
</html>
