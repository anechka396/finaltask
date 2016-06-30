<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${requestScope.topics == null}">
    <c:redirect url="Controller?command=get-topics"/>
</c:if>

<html>
<head>
    <title>Topics management</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

    <fmt:setLocale value="${cookie.locale.value}"/>
    <fmt:setBundle basename="localization.prop" var="localization"/>
    <fmt:message bundle="${localization}" key="prop.topic.name" var="topicName"/>
    <fmt:message bundle="${localization}" key="prop.remove" var="remove"/>
    <fmt:message bundle="${localization}" key="prop.save" var="save"/>
    <fmt:message bundle="${localization}" key="prop.cancel" var="cancel"/>
    <fmt:message bundle="${localization}" key="prop.topic.add" var="addTopic"/>
    <fmt:message bundle="${localization}" key="prop.topic.mes.edit" var="editMessage"/>
    <fmt:message bundle="${localization}" key="prop.topic.mes.remove" var="removeMessage"/>
</head>
<body>

<div class="container-fluid">
    <c:import url="/fragments/navbar.jsp"/>
    <div class="row main-row">
        <div class="col-sm-12 table-responsive">
            <table class="table table-hover text-center" id="topics2">
                <thead>
                    <tr>
                        <td>${topicName} (${editMessage})</td>
                        <td>${remove} (${removeMessage})</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.topics}" var="topic">
                    <tr>
                        <td>
                            <a href="#" class="editable" data-type="text" data-pk="${topic.id}" data-url="/Controller"><c:out value="${topic.value}"/></a>
                        </td>
                        <td>
                            <form method="post" action="/Controller">
                                <input type="hidden" name="command" value="delete-topic">
                                <input type="hidden" name="pk" value="${topic.id}">
                                <input type="submit" class="btn-link" value="${remove}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button class="btn btn-success" id="btn-add-topic" data-toggle="modal" data-target="#addTopicModal">
                ${addTopic}
            </button>
        </div>
    </div>
    <c:import url="/fragments/footer.jsp"/>
</div>

<div class="modal fade" id="addTopicModal" tabindex="-1" role="dialog" aria-labelledby="addTopicModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addTopicModalLabel">${addTopic}</h4>
            </div>
            <div class="modal-body">
                <form id="add-topic-form" action="/Controller" method="post">
                    <div class="form-group">
                        <input type="hidden" name="command" value="add-topic">
                        <div class="form-group">
                            <label for="value" class="control-label">${topicName}</label>
                            <input type="text" id="value" name="value" value="" class="form-control" required>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" form="add-topic-form" class="btn btn-primary">${save}</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">${cancel}</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $.fn.editable.defaults.mode = 'inline';
        $('.editable').editable({
            params: {
                command: 'edit-topic'
            }
        });
    });
</script>
</body>
</html>
