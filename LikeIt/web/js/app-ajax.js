$(document).ready(function() {
    $('#topics').on('show.bs.collapse', function() {
        $.ajax({
            url : '/Controller',
            data : {
                command: 'all-topics'
            },
            dataType : 'json',
            success : function(responseText) {
                var url = '/Controller?command=last&topic=';
                $('#ajaxGetUserServletResponse').empty();
                $.each(responseText, function(index, item) {
                    $.each(item, function( index, value ) {
                        var start="<a class='btn btn-default' href=" + url + value + ">";
                        var end="</a>"
                        $('#ajaxGetUserServletResponse').append(start + value + end);
                    });
                });
            }
        });
    });

    $(".close-question").click(function(){
        $.ajax({
            url : '/Controller',
            data : {
                command: 'delete-question',
                id: $(this).attr("data-id")
            }
        });

        $(this).parent().remove();
    });
});

