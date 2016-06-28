$(document).ready(function() {
    $('.dropdown').on('show.bs.dropdown', function() {
        $.ajax({
            url : '/Controller',
            data : {
                command: 'all-topics'
            },
            dataType : 'json',
            success : function(responseText) {
                var url = '/Controller?command=last&topic=';
                var str = "";
                $.each(responseText, function(index, item) {
                    $.each(item, function( index, value ) {
                        str += "<li><a href="+url+value+">"+value+"</a></li> ";
                    });
                });
                $('#topics').empty().prepend(str);
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

