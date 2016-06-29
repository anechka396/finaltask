<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<html>
<head>
    <title>Title</title>
    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.lang.ru" var="ru"/>
    <fmt:message bundle="${localization}" key="prop.lang.en" var="en"/>
    <fmt:message bundle="${localization}" key="prop.author" var="author"/>
</head>
<body>
    <footer style="text-align: center">
        <div class="row">
            <mytag:info-date/>
        </div>
        <div class="row">
            <span>${author}</span>
        </div>
        <div class="row">
            <div class="col-sm-2 col-sm-offset-4 text-right">
                <form action="Controller" method="post" class="lang_button">
                    <input type="hidden" name="command" value="locale">
                    <input type="hidden" name="locale" value="en">
                    <input type="submit" class="btn-link" value="${en}"/>
                </form>
            </div>
            <div class="col-sm-2 text-left">
                <form action="Controller" method="post" class="lang_button">
                    <input type="hidden" name="command" value="locale">
                    <input type="hidden" name="locale" value="ru">
                    <input type="submit" class="btn-link"  value="${ru}">
                </form>
            </div>
        </div>
    </footer>
</body>
</html>
