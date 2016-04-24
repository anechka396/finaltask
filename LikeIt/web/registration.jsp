<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop" var="lang"/>
    <fmt:message bundle="${lang}" key="prop.send" var="send"/>
    <fmt:message bundle="${lang}" key="prop.login.login" var="login"/>
    <fmt:message bundle="${lang}" key="prop.login.password" var="password"/>
    <fmt:message bundle="${lang}" key="prop.login.name" var="name"/>
    <fmt:message bundle="${lang}" key="prop.login.email" var="email"/>
    <fmt:message bundle="${lang}" key="prop.title.reg" var="regTitle"/>
    <fmt:message bundle="${lang}" key="prop.title.newaccount" var="newAccountTitle"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 text-center" style="background: rgba(255,255,255, 0.5); padding: 10px">
            <h1>${regTitle}. <small>${newAccountTitle}.</small></h1>
            <form action="Controller" method="post" class="form-horizontal text-left">
                <input type="hidden" name="command" value="registration">
                <div class="form-group">
                    <label for="login" class="col-sm-3 control-label">${login}*</label>
                    <div class="col-sm-9">
                        <input type="text" name="login" id="login" value="" class="form-control" required maxlength="30">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">${password}*</label>
                    <div class="col-sm-9">
                        <input type="password" name="password" id="password" value="" class="form-control" required maxlength="30">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">${name}</label>
                    <div class="col-sm-9">
                        <input type="text" name="name" id="name" value="" class="form-control" maxlength="30">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">${email}*</label>
                    <div class="col-sm-9">
                        <input type="email" name="email" id="email" value="" class="form-control" required>
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
</body>
</html>
