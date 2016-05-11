$(document).ready(function() {
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
