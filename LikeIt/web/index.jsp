<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${requestScope.questions == null}">
  <c:redirect url="Controller?command=last"/>
</c:if>


<!DOCTYPE html>
<html>
  <head>
    <title>Main Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.login" var="login"/>
    <fmt:message bundle="${localization}" key="prop.logout" var="logout"/>
    <fmt:message bundle="${localization}" key="prop.registration" var="reg"/>
    <fmt:message bundle="${localization}" key="prop.search" var="search"/>
    <fmt:message bundle="${localization}" key="prop.question.add" var="addQuestion"/>
    <fmt:message bundle="${localization}" key="prop.question.type" var="typeQuestion"/>
    <fmt:message bundle="${localization}" key="prop.hello" var="hello"/>
    <fmt:message bundle="${localization}" key="prop.ask" var="ask"/>
    <fmt:message bundle="${localization}" key="prop.in" var="in"/>
    <fmt:message bundle="${localization}" key="prop.topics" var="topics"/>

  </head>
  <body>

  <div class="container-fluid">
    <c:import url="/fragments/navbar.jsp"/>

    <div class="row main-row">
      <div class="col-xs-12">
      <div class="col-xs-offset-1 col-sm-offset-2 col-xs-10 col-sm-8">
        <c:if test="${sessionScope.user != null}">
          <form action="/AddQuestionPage" method="post" class="form" style="margin: 10px 5px">
            <div class="form-group">
              <textarea rows="5" class="form-control" placeholder="${typeQuestion}" name="text"></textarea>
            </div>
            <input type="submit" value="${addQuestion}" class="btn btn-primary">
          </form>
        </c:if>


        <div id="posts" data-dt="hello">
          <c:forEach items="${requestScope.questions}" var="question">
            <div class="well well-sm" data-date="${question.date}">
              <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.login == question.author}">
                <button class="close close-question" data-id="${question.id}"><span aria-hidden="true">&times;</span></button>
              </c:if>
              <form action="/QuestionPage" method="post">
                <input type="hidden" name="id" value="${question.id}">
                <input type="submit" value="<c:out value="${question.text}"/>" class="btn-link" style="white-space: normal; width: 100%; font-size: 20px; text-align: left">
              </form>
              <p>
                <a href="#" class="text-muted"><c:out value="${question.author}"/></a>
                <span class="text-muted">${in}</span>
                <a class="text-muted"><c:out value="${question.topic}"/></a>
                <c:if test="${sessionScope.user != null}">
                  <span class="text-muted"> &bull; </span>
                  <a class="text-muted" href="/QuestionPage?id=${question.id}#answer-text"><span class="glyphicon glyphicon-comment" style="font-size: 12px"></span>${ask}</a>
                </c:if>
              </p>
            </div>
          </c:forEach>
        </div>
        <div id="loading">
          <img alt="Loading...">
        </div>
      </div>
     </div>
    </div>
    <c:import url="/fragments/footer.jsp"/>
    </div>

  <script>
    $(document).ready(function() {
      $(window).scroll(function(){
        if($(window).scrollTop() == $(document).height() - $(window).height()){
          $('#loading').show();
          var date = $('#posts div:last-child').attr('data-date');
          console.log(date);

        /*  $.ajax({
            url: 'get-post.php',
            dataType: 'html',
            success: function(html) {
              $('#posts').append(html);
              $('#loading').hide();
            }
          });*/
        }
      })
    });
  </script>

  </body>
</html>
