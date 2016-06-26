<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user == null}">
    <c:redirect url="/Error"/>
</c:if>

<c:set var="user" value="${sessionScope.user}"/>

<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/validator.min.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="loc"/>
    <fmt:message bundle="${loc}" key="prop.old.password" var="oldPassword"/>
    <fmt:message bundle="${loc}" key="prop.new.password" var="newPassword"/>
    <fmt:message bundle="${loc}" key="prop.repeat.new.password" var="repeatNewPassword"/>
    <fmt:message bundle="${loc}" key="prop.close" var="close"/>
    <fmt:message bundle="${loc}" key="prop.save" var="save"/>
    <fmt:message bundle="${loc}" key="prop.err.required" var="errRequired"/>
    <fmt:message bundle="${loc}" key="prop.err.min2length" var="errLength2"/>
    <fmt:message bundle="${loc}" key="prop.err.min5length" var="errLength5"/>
    <fmt:message bundle="${loc}" key="prop.err.pattern" var="errPattern"/>
    <fmt:message bundle="${loc}" key="prop.err.email" var="errEmail"/>
    <fmt:message bundle="${loc}" key="prop.login.login" var="login"/>
    <fmt:message bundle="${loc}" key="prop.login.name" var="name"/>
    <fmt:message bundle="${loc}" key="prop.login.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="prop.login.email" var="email"/>
    <fmt:message bundle="${loc}" key="prop.save" var="save"/>
    <fmt:message bundle="${loc}" key="prop.cancel" var="cancel"/>
</head>
<body>
<div class="container">
    <c:import url="/navbar.jsp"/>
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 text-center" style="background: rgba(255,255,255, 0.5); padding: 10px">
            <form id="editForm" action="Controller" method="post" class="form-horizontal text-left" data-toggle="validator">
                <input type="hidden" name="command" value="edit-profile">
                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger alert-dismissible" role="alert" data-message="${requestScope.error}">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            ${requestScope.error}
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">${name}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="name" id="name" value="${user.name}" class="form-control"
                               data-minlength="2" data-minlength-error="${errLength2}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="surname" class="col-sm-3 control-label">${surname}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="surname" id="surname" value="${user.lastName}" class="form-control"
                               data-minlength="2" data-minlength-error="${errLength2}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="login" class="col-sm-3 control-label">${login}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="login" id="login" value="${user.login}" class="form-control"
                               data-minlength="5" data-minlength-error="${errLength5}"
                               data-pattern="^\w+$" data-pattern-error="${errPattern}"
                               required data-error="${errRequired}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">${email}*</label>
                    <div class="col-sm-9">
                        <input type="email" name="email" id="email" value="${user.email}" class="form-control"
                               data-error="${errEmail}"
                        >
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary">${save}</button>
                        <a href="/UserPage" class="btn btn-danger">${cancel}</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $('#editForm').validator({
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
