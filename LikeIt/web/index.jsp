<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${requestScope.questions == null}">
  <c:redirect url="Controller?command=last"/>
</c:if>

<html>
  <head>
    <title>Main Page</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="js/app-ajax.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="loc"/>
    <fmt:message bundle="${loc}" key="prop.login" var="login"/>
    <fmt:message bundle="${loc}" key="prop.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="prop.registration" var="reg"/>
    <fmt:message bundle="${loc}" key="prop.lang.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="prop.lang.en" var="en"/>
    <fmt:message bundle="${loc}" key="prop.search" var="search"/>
    <fmt:message bundle="${loc}" key="prop.question.add" var="addQuestion"/>
    <fmt:message bundle="${loc}" key="prop.question.type" var="typeQuestion"/>
    <fmt:message bundle="${loc}" key="prop.hello" var="hello"/>
  </head>
  <body>

  <video autoplay loop muted class="bgvideo" id="bgvideo">
    <source src="source/bg-max.mp4" type="video/mp4">
  </video>

  <div class="container-fluid">
    <div class="row">
      <div class="col-xs-12">
        <nav class="navbar navbar-default">
          <div class="container-fluid">

            <div class="navbar-header">
              <a class="navbar-brand" href="#">Like It</a>
            </div>

            <div class="collapse navbar-collapse">
              <ul class="nav navbar-nav">
                <li><a data-toggle="collapse" data-target="#topics" id="show-topics">Topics</a></li>
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


    <div class="row" style="padding: 0px 15px">
      <div class="col-xs-12"  style="background: rgba(255,255,255, 0.5); padding: 10px">
      <div class="col-xs-offset-1 col-sm-offset-2 col-xs-10 col-sm-8">

        <c:if test="${sessionScope.user != null}">
          <form action="question.jsp" method="post" class="form" style="margin: 10px 5px">
            <div class="form-group">
              <textarea rows="5" class="form-control" placeholder="${typeQuestion}" name="text"></textarea>
            </div>
            <input type="submit" value="${addQuestion}" class="btn btn-primary">
          </form>
        </c:if>


        <c:forEach items="${requestScope.questions}" var="question">
          <div class="well">
            <p><a href="#"><c:out value="${question.text}"/></a></p>
            <p>
              <a href="#"><c:out value="${question.author}"/></a>
              in
              <a><c:out value="${question.topic}"/></a>
              <c:if test="${sessionScope.user != null}">
                <a><span class="glyphicon glyphicon-comment"></span>Ответить</a>
              </c:if>
            </p>
          </div>
        </c:forEach>

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
