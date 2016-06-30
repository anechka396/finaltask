<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user eq null}">
    <c:redirect url="/Error"/>
</c:if>

<html>
<head>
    <title>Change password</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/validator.min.js"></script>

    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.old.password" var="oldPassword"/>
    <fmt:message bundle="${localization}" key="prop.new.password" var="newPassword"/>
    <fmt:message bundle="${localization}" key="prop.repeat.new.password" var="repeatNewPassword"/>
    <fmt:message bundle="${localization}" key="prop.err.required" var="errRequired"/>
    <fmt:message bundle="${localization}" key="prop.err.min5length" var="errLength5"/>
    <fmt:message bundle="${localization}" key="prop.err.match" var="errMatch"/>
</head>
<body>
    <div class="container-fluid">
        <c:import url="/fragments/navbar.jsp"/>
        <div class="row main-row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="form-horizontal text-left" method="post" action="/Controller"  data-toggle="validator">
                    <input type="hidden" name="command" value="change-password">
                    <div class="row form-group">
                        <label for="old-password" class="col-sm-4 control-label">${oldPassword}:</label>
                        <div class="col-sm-8">
                            <input type="password" name="old-password" id="old-password" class="form-control"
                                   data-minlength="5" data-minlength-error="${errLength}"
                                   required data-error="${errRequired}"
                            >
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="new-password" class="col-sm-4 control-label">${newPassword}:</label>
                        <div class="col-sm-8">
                            <input type="password" name="new-password" id="new-password" class="form-control"
                                   data-minlength="5" data-minlength-error="${errLength}"
                                   required data-error="${errRequired}"
                            >
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="new-password-2" class="col-sm-4 control-label">${repeatNewPassword}:</label>
                        <div class="col-sm-8">
                            <input type="password" name="new-password-2" id="new-password-2" class="form-control"
                                   data-minlength="5" data-minlength-error="${errLength}"
                                   data-match="#new-password" data-match-error="${errMatch}"
                                   required data-error="${errRequired}"
                            >
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-8">
                            <input type="submit" class="btn btn-primary">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
