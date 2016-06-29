<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.user.role ne 'ADMIN'}">
    <c:redirect url="/Error"/>
</c:if>

<html>;
<head>
    <title>Topics management</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
</head>
<body>

<div class="container-fluid">
    <c:import url="/fragments/navbar.jsp"/>
    <div class="row main-row">
        <div class="col-sm-10 col-sm-offset-1">
            <div class="table-responsive">
                <table class="table table-hover" id="topics2">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Topic</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <c:import url="/fragments/footer.jsp"/>
</div>

<script>
    $(document).ready(function() {
        $.ajax({
            url : '/Controller',
            data : {
                command: 'all-topics'
            },
            dataType : 'json',
            success : function(responseText) {
                var str = "";
                var i = 1;
                $.each(responseText, function(index, item) {
                    $.each(item, function( index, value ) {
                        str += "<tr><td>"+i+++"</td><td>"+value+"</td></tr> ";
                    });
                });
                $('#topics2 tbody').empty().prepend(str);
            }
        });
    });
</script>
</body>
</html>
