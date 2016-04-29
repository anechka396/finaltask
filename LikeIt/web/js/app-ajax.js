$(document).ready(function() {
    $('#topics').on('show.bs.collapse', function() {
        $.ajax({
            url : '/Controller',
            data : {
                command: 'all-topics'
            },
            dataType : 'json',
            success : function(responseText) {
                $('#ajaxGetUserServletResponse').empty();
                $.each(responseText, function(index, item) {
                    $.each(item, function( index, value ) {
                        $('#ajaxGetUserServletResponse').append("<a class='btn btn-default'>" + value + "</a>");
                    });
                });
            }
        });
    });
});

