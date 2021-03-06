<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Registration page</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/validator.min.js"></script>

    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.send" var="send"/>
    <fmt:message bundle="${localization}" key="prop.login.login" var="login"/>
    <fmt:message bundle="${localization}" key="prop.login.password" var="password"/>
    <fmt:message bundle="${localization}" key="prop.login.password2" var="password2"/>
    <fmt:message bundle="${localization}" key="prop.login.name" var="name"/>
    <fmt:message bundle="${localization}" key="prop.login.surname" var="surname"/>
    <fmt:message bundle="${localization}" key="prop.login.email" var="email"/>
    <fmt:message bundle="${localization}" key="prop.title.reg" var="regTitle"/>
    <fmt:message bundle="${localization}" key="prop.title.newaccount" var="newAccountTitle"/>
    <fmt:message bundle="${localization}" key="prop.err.required" var="errRequired"/>
    <fmt:message bundle="${localization}" key="prop.err.min2length" var="errLength2"/>
    <fmt:message bundle="${localization}" key="prop.err.min5length" var="errLength5"/>
    <fmt:message bundle="${localization}" key="prop.err.pattern" var="errPattern"/>
    <fmt:message bundle="${localization}" key="prop.err.match" var="errMatch"/>
    <fmt:message bundle="${localization}" key="prop.err.email" var="errEmail"/>
    <fmt:message bundle="${localization}" key="prop.errorLoginOrPassword" var="errorLoginOrPassword"/>
    <fmt:message bundle="${localization}" key="prop.errorInvalidParams" var="invalidParams"/>
</head>
<body>
<div class="container-fluid">
    <c:import url="fragments/navbar.jsp"/>
    <div class="row main-row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 text-center">
            <h1>${regTitle}. <small>${newAccountTitle}.</small></h1>

            <form id="registrationForm" action="Controller" method="post" class="form-horizontal text-left" data-toggle="validator">
                <input type="hidden" name="command" value="registration">
                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger alert-dismissible" role="alert" data-message="${requestScope.error}">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        ${requestScope.error}
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="login" class="col-sm-3 control-label">${login}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="login" id="login" value="" class="form-control"
                               data-minlength="5" data-minlength-error="${errLength5}"
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
                               data-minlength="5" data-minlength-error="${errLength5}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password2" class="col-sm-3 control-label">${password2}*</label>
                    <div class="col-sm-9">
                        <input type="password" name="password2" id="password2" value="" class="form-control"
                               data-minlength="5" data-minlength-error="${errLength5}"
                               data-match="#password" data-match-error="${errMatch}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">${name}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="name" id="name" value="" class="form-control"
                               data-minlength="2" data-minlength-error="${errLength2}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="surname" class="col-sm-3 control-label">${surname}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="surname" id="surname" value="" class="form-control"
                               data-minlength="2" data-minlength-error="${errLength2}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">${email}*</label>
                    <div class="col-sm-9">
                        <input type="email" name="email" id="email" value="" class="form-control"
                               data-error="${errEmail}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary">${send}</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <c:import url="/fragments/footer.jsp"/>
</div>

<script>
    $('#registrationForm').validator({
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
