<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:if test="${requestScope.error != null}">

</c:if>

<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/validator.min.js"></script>
    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.send" var="send"/>
    <fmt:message bundle="${localization}" key="prop.login.login" var="login"/>
    <fmt:message bundle="${localization}" key="prop.login.password" var="password"/>
    <fmt:message bundle="${localization}" key="prop.err.required" var="errRequired"/>
    <fmt:message bundle="${localization}" key="prop.err.min5length" var="errLength"/>
    <fmt:message bundle="${localization}" key="prop.err.pattern" var="errPattern"/>
    <fmt:message bundle="${localization}" key="prop.error.loginOrPassword" var="errorLoginOrPassword"/>
    <fmt:message bundle="${localization}" key="prop.error.invalidParams" var="invalidParams"/>
</head>
<body>

<div class="container-fluid">
    <c:import url="fragments/navbar.jsp"/>
    <div class="row main-row">
        <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <form id="loginForm" action="Controller" method="post" class="form-horizontal" data-toggle="validator">
                <input type="hidden" name="command" value="login">
                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger alert-dismissible" role="alert" data-message="${requestScope.error}">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <c:set var="errLoginOrPas" value="errorLoginOrPassword"/>
                        <c:set var="errParams" value="errorParams"/>
                        <c:choose>
                            <c:when test="${requestScope.error == errLoginOrPas}">
                                ${errorLoginOrPassword}
                            </c:when>
                            <c:when test="${requestScope.error == errParams}">
                                ${invalidParams}
                            </c:when>
                        </c:choose>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="login" class="col-sm-3 control-label">${login}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="login" id="login" value="" class="form-control"
                               data-minlength="5" data-minlength-error="${errLength}"
                               data-pattern="^\w+$" data-pattern-error="${errPattern}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">${password}*</label>
                    <div class="col-sm-9">
                        <input type="password" name="password" id="password" value="" class="form-control"
                               data-minlength="5" data-minlength-error="${errLength}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-default">${send}</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <c:import url="/fragments/footer.jsp"/>
</div>

<script>
    $('#loginForm').validator({
        custom: {
            pattern: function ($el) {
                var pattern = $el.data('pattern')
                return !$el.val() || new RegExp(pattern,"g").test($el.val())
            }
        },
        errors: {
            pattern: "Illegal characters"
        }
    })
</script>

</body>
</html>
