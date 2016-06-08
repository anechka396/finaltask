<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/validator.min.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="loc"/>
    <fmt:message bundle="${loc}" key="prop.send" var="send"/>
    <fmt:message bundle="${loc}" key="prop.login.login" var="login"/>
    <fmt:message bundle="${loc}" key="prop.login.password" var="password"/>
    <fmt:message bundle="${loc}" key="prop.login.password2" var="password2"/>
    <fmt:message bundle="${loc}" key="prop.login.name" var="name"/>
    <fmt:message bundle="${loc}" key="prop.login.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="prop.login.email" var="email"/>
    <fmt:message bundle="${loc}" key="prop.title.reg" var="regTitle"/>
    <fmt:message bundle="${loc}" key="prop.title.newaccount" var="newAccountTitle"/>
    <fmt:message bundle="${loc}" key="prop.err.required" var="errRequired"/>
    <fmt:message bundle="${loc}" key="prop.err.min2length" var="errLength2"/>
    <fmt:message bundle="${loc}" key="prop.err.min5length" var="errLength5"/>
    <fmt:message bundle="${loc}" key="prop.err.pattern" var="errPattern"/>
    <fmt:message bundle="${loc}" key="prop.err.match" var="errMatch"/>
    <fmt:message bundle="${loc}" key="prop.err.email" var="errEmail"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 text-center" style="background: rgba(255,255,255, 0.5); padding: 10px">
            <h1>${regTitle}. <small>${newAccountTitle}.</small></h1>

            <form id="registrationForm" action="Controller" method="post" class="form-horizontal text-left" data-toggle="validator">
                <input type="hidden" name="command" value="registration">
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
