<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
  <head>
    <title>Main Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
      body {
        padding-top: 20px;
      }

      .btn-link{
        background: transparent;
        border: none;
      }

      .btn-link:hover{
        text-decoration: none;
      }

    </style>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="prop" var="lang"/>
    <fmt:message bundle="${lang}" key="prop.login" var="login"/>
    <fmt:message bundle="${lang}" key="prop.logout" var="logout"/>
    <fmt:message bundle="${lang}" key="prop.registration" var="reg"/>
  </head>
  <body>

  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <nav class="navbar navbar-default" style="background-color: lightblue">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <a class="navbar-brand" href="#">LikeIt</a>
            </div>

            <div class="collapse navbar-collapse">
              <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                  <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
              </form>

                <c:choose>
                  <c:when test="${sessionScope.login == null}">
                    <ul class="nav navbar-nav navbar-right">
                      <li><a href="registration.jsp">${reg}</a></li>
                      <li><a href="login.jsp">${login}</a</li>
                    </ul>
                  </c:when>
                  <c:otherwise>
                    <form action="Controller" method="post" class="navbar-form navbar-right">
                      <input type="hidden" name="command" value="logout">
                      <button type="submit" class="btn btn-link">
                        ${logout}
                      </button>
                    </form>
                    <p class="navbar-text navbar-right">Hello, <a><c:out value="${sessionScope.login.login}"/></a>!</p>
                  </c:otherwise>
                </c:choose>
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav>
        </div>
      <div class="col-md-12">
        <form action="Controller" method="post" class="lang_button">
          <input type="hidden" name="command" value="locale">
          <input type="hidden" name="locale" value="en">
          <input type="submit" class="text-button" value="EN"/>
        </form>

        <form action="Controller" method="post" class="lang_button">
          <input type="hidden" name="command" value="locale">
          <input type="hidden" name="locale" value="ru">
          <input type="submit" class="text-button"  value="RU">
        </form>
      </div>
      </div>
    </div>

  </body>
</html>
