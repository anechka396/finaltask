<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user == null}">
    <c:redirect url="/Error"/>
</c:if>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/star-rating.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/star-rating.min.js"></script>
    <script src="/js/validator.min.js"></script>
    <script src="/js/bootsrap-file-input.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.prop" var="loc"/>
    <fmt:message bundle="${loc}" key="prop.login.name" var="name"/>
    <fmt:message bundle="${loc}" key="prop.login.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="prop.login.login" var="login"/>
    <fmt:message bundle="${loc}" key="prop.login.email" var="email"/>
    <fmt:message bundle="${loc}" key="prop.rating" var="rating"/>
    <fmt:message bundle="${loc}" key="prop.change.image" var="changeImage"/>
    <fmt:message bundle="${loc}" key="prop.change.password" var="changePassword"/>
    <fmt:message bundle="${loc}" key="prop.old.password" var="oldPassword"/>
    <fmt:message bundle="${loc}" key="prop.new.password" var="newPassword"/>
    <fmt:message bundle="${loc}" key="prop.repeat.new.password" var="repeatNewPassword"/>
    <fmt:message bundle="${loc}" key="prop.close" var="close"/>
    <fmt:message bundle="${loc}" key="prop.save" var="save"/>
    <fmt:message bundle="${loc}" key="prop.err.required" var="errRequired"/>
    <fmt:message bundle="${loc}" key="prop.err.min5length" var="errLength5"/>
    <fmt:message bundle="${loc}" key="prop.err.match" var="errMatch"/>
    <fmt:message bundle="${loc}" key="prop.select.file" var="selectFile"/>
</head>
<body>
    <div class="container-fluid">
        <c:import url="/navbar.jsp"/>
        <div class="row">
            <div class="col-sm-2">
                <div class="btn-group-vertical" role="group" aria-label="...">
                    <img src="${sessionScope.user.imageURL}" width="100%">
                    <button class="btn btn-primary" id="btn-change-image" data-toggle="modal" data-target="#imageModal">
                        ${changeImage}
                    </button>
                    <button class="btn btn-primary" id="btn-change-password" data-toggle="modal" data-target="#myModal">
                        ${changePassword}
                    </button>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="row">
                    <div class="col-sm-3">
                        <span>${name}:</span>
                    </div>
                    <div class="col-sm-8 well well-sm">
                        <span>${sessionScope.user.name}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <span>${surname}:</span>
                    </div>
                    <div class="col-sm-8 well well-sm">
                        <span>${sessionScope.user.lastName}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <span>${login}:</span>
                    </div>
                    <div class="col-sm-8 well well-sm">
                        <span id="login">${sessionScope.user.login}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <span>${email}:</span>
                    </div>
                    <div class="col-sm-8 well well-sm">
                        <span>${sessionScope.user.email}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <span>${rating}:</span>
                    </div>
                    <div class="col-sm-8">
                        <input id="rating" type="number" class="rating-loading">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">${changePassword}</h4>
                </div>
                <div class="modal-body">
                    <form id="change-password-form" data-toggle="validator">
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
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" id="change-password" form="change-password-form" class="btn btn-primary">${save}</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">${close}</button>
                </div>
            </div>
        </div>
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
                    <button type="submit" form="change-image-form" class="btn btn-primary">${save}</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">${close}</button>
                </div>
            </div>
        </div>
    </div>

<script>
    $(document).on('ready', function(){

       // $('input[type=file]').bootstrapFileInput();

        $('#rating').rating({
            step: 0.1,
            size: 'xs',
            displayOnly: true
        });

        $.ajax({
            url : '/Controller',
            data : {
                command: 'get-user-rating',
                login: $('#login').text()
            },
            success : function(responseText) {
                console.log("'" +responseText + "'");
                $('#rating').rating('update', responseText)
            }
        });

        $('#myModal').on('show.bs.modal', function(e){
            $('#change-password-form')[0].reset();
        });

        $('#change-password-form').on('invalid.bs.validator', function(e){
            $('#change-password').attr('disabled','disabled');
        });

        /*$('#imageModal').on('show.bs.modal', function(e){
            $('#change-image-form')[0].reset();
        });*/

        $('#change-password-form').submit(function(e){
            $.ajax({
                url: '/Controller',
                type: 'post',
                data: {
                    command: 'change-password',
                    oldPassword: $('#old-password').val(),
                    newPassword: $('#new-password').val(),
                    repeatNewPassword: $('#new-password-2').val()
                },
                success: function(data){
                    $('#myModal').modal('hide');
                }
            });

            e.preventDefault();
        });

    });
</script>
</body>
</html>
