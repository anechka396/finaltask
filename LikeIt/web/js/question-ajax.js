$(document).ready(function() {
    $('#edit-question').css({'border': 'none'});

    $('#edit-question').editable({
        type: 'textarea',
        placement: 'bottom',
        title: 'Edit question',
        params: {
            command: 'edit-question'
        }
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

    $('.rating').on('rating.change', function() {
        $.ajax({
            url : '/Controller',
            data : {
                command: 'set-rating',
                id: $(this).attr("data-id"),
                mark: $(this).val()
            }
        });
    });

});
