<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user == null}">
    <c:redirect url="/Error"/>
</c:if>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/star-rating.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/star-rating.min.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
</head>
<body>
    <div class="container-fluid">
        <c:import url="/navbar.jsp"/>
        <div class="row">
            <div class="col-sm-2">
                <div class="btn-group-vertical" role="group" aria-label="...">
                    <img src="../../pictures/no-avatar.png" width="100%">
                    <button class="btn btn-primary">
                        Изменить изобружение
                    </button>
                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                        Изменить пароль
                    </button>
                </div>
            </div>
            <div class="col-sm-10">
                <div class="row">
                    <div class="col-sm-2">
                        Имя:
                    </div>
                    <div class="col-sm-10">
                        ${sessionScope.user.name}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        Фамилия:
                    </div>
                    <div class="col-sm-10">
                        ${sessionScope.user.lastName}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        login:
                    </div>
                    <div class="col-sm-10">
                        <span id="login">${sessionScope.user.login}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        e-mail:
                    </div>
                    <div class="col-sm-10">
                        ${sessionScope.user.email}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <span>Рейтинг:</span>
                    </div>
                    <div class="col-sm-10">
                        <input id="rating" type="number" class="rating-loading">
                    </div>
                </div>
            </div>
        </div>
        <a href="../../index.jsp">To main page</a>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Изменить пароль</h4>
                </div>
                <div class="modal-body">
                    <form id="change-password-form">
                        <div class="row form-group">
                            <label for="old-password" class="col-sm-4 control-label">Старый пароль:</label>
                            <div class="col-sm-8">
                                <input type="password" name="old-password" id="old-password" class="form-control" required>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label for="new-password" class="col-sm-4 control-label">Новый пароль:</label>
                            <div class="col-sm-8">
                                <input type="password" name="new-password" id="new-password" class="form-control" required>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label for="new-password-2" class="col-sm-4 control-label">Повторите новый пароль:</label>
                            <div class="col-sm-8">
                                <input type="password" name="new-password-2" id="new-password-2" class="form-control" required>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" form="change-password-form" class="btn btn-primary">Save</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
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
                login: $('#login').text()
            },
            success : function(responseText) {
                console.log("'" +responseText + "'");
                $('#rating').rating('update', responseText)
            }
        });

    });
</script>
</body>
</html>
