$(document).ready(function() {
    $('#new-answer').click(function() {
        var text=$('#answer-text').val();
        $.ajax({
            url: '/Controller',
            data: {
                command: 'add-answer',
                text: text,
                id: $(this).attr("data-id")
            },
            dataType : 'json',
            success: function (response) {
                $('#answers').empty();
                $('#IPhotoTmpl').tmpl(response).appendTo('#answers');
                $('#answer-text').val('');
            }
        });
    });

    $(".close").click(function(){
        $.ajax({
            url : '/Controller',
            data : {
                command: 'delete-answer',
                id: $(this).attr("data-id")
            }
        });

        $(this).parent().remove();
    });
});
