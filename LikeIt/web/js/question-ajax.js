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
            success: function () {
                $('#answers').append(text);
                $('#answer-text').val('');
            }
        });
    });
});
