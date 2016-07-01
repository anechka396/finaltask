<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>User page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/star-rating.min.js"></script>
    <script src="/js/validator.min.js"></script>

    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.login.name" var="name"/>
    <fmt:message bundle="${localization}" key="prop.login.surname" var="surname"/>
    <fmt:message bundle="${localization}" key="prop.login.login" var="login"/>
    <fmt:message bundle="${localization}" key="prop.login.email" var="email"/>
    <fmt:message bundle="${localization}" key="prop.rating" var="rating"/>
    <fmt:message bundle="${localization}" key="prop.change.image" var="changeImage"/>
    <fmt:message bundle="${localization}" key="prop.change.password" var="changePassword"/>
    <fmt:message bundle="${localization}" key="prop.close" var="close"/>
    <fmt:message bundle="${localization}" key="prop.save" var="save"/>
    <fmt:message bundle="${localization}" key="prop.err.required" var="errRequired"/>
    <fmt:message bundle="${localization}" key="prop.err.min5length" var="errLength5"/>
    <fmt:message bundle="${localization}" key="prop.err.match" var="errMatch"/>
    <fmt:message bundle="${localization}" key="prop.select.file" var="selectFile"/>
    <fmt:message bundle="${localization}" key="prop.edit.user" var="editProfile"/>
</head>
<body>
    <div class="container-fluid">
        <c:import url="/fragments/navbar.jsp"/>
        <div class="row main-row">
            <div class="col-md-3 col-md-offset-1 col-sm-4 col-sm-offset-0">
                <div class="btn-group-vertical" role="group">
                    <c:choose>
                        <c:when test="${empty sessionScope.user.imageURL}">
                            <img src="/pictures/no-avatar.png" width="100%">
                        </c:when>
                        <c:otherwise>
                            <img src="${sessionScope.user.imageURL}" width="100%">
                        </c:otherwise>
                    </c:choose>
                    <button class="btn btn-primary" id="btn-change-image" data-toggle="modal" data-target="#imageModal">
                        ${changeImage}
                    </button>
                    <a href="/EditUserPage" class="btn btn-primary" id="btn-edit-profile">
                        ${editProfile}
                    </a>
                    <a href="/ChangePassword" class="btn btn-primary" id="btn-change-password">
                        ${changePassword}
                    </a>
                </div>
            </div>
            <div class="col-md-7 col-sm-8">
                <form role="form">
                    <div class="form-group">
                        <label for="name">${name}:</label>
                        <input type="text" class="form-control" id="name" value="<c:out value="${sessionScope.user.name}"/>" disabled>
                    </div>
                    <div class="form-group">
                        <label for="lastName">${surname}:</label>
                        <input type="text" class="form-control" id="lastName" value="<c:out value="${sessionScope.user.lastName}"/>" disabled>
                    </div>
                    <div class="form-group">
                        <label for="login">${login}:</label>
                        <input type="text" class="form-control" id="login" value="<c:out value="${sessionScope.user.login}"/>" disabled>
                    </div>
                    <div class="form-group">
                        <label for="email">${email}:</label>
                        <input type="text" class="form-control" id="email" value="<c:out value="${sessionScope.user.email}"/>" disabled>
                    </div>
                    <div class="form-group">
                        <label for="rating">${rating}:</label>
                        <input id="rating" type="number" class="rating-loading">
                    </div>
                </form>
            </div>
        </div>
        <c:import url="/fragments/footer.jsp"/>
    </div>

    <div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="imageModalLabel">${changeImage}</h4>
                </div>
                <div class="modal-body">
                    <form id="change-image-form" action="/Controller/loadImage" method="post" data-toggle="validator" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="file" name="file" id="file" title="${selectFile}" accept="image/*" required>
                            <div class="help-block with-errors"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" id="submitImageForm" form="change-image-form" class="btn btn-primary">${save}</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">${close}</button>
                </div>
            </div>
        </div>
    </div>

<script>
    $(document).on('ready', function(){

        $('#rating').rating({
            step: 0.1,
            size: 'xs',
            displayOnly: true
        });

        $.ajax({
            url : '/Controller',
            data : {
                command: 'get-user-rating',
                login: $('#login').val()
            },
            success : function(responseText) {
                $('#rating').rating('update', responseText);
            }
        });
    });
</script>
</body>
</html>
