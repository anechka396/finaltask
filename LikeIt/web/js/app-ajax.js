$(document).ready(function() {
    var cntr = "/Controller";
    $('.dropdown').on('show.bs.dropdown', function() {
        $.ajax({
            url : cntr,
            data : {
                command: 'all-topics'
            },
            dataType : 'json',
            success : function(responseText) {
                var url = cntr+'?command=last-questions&topic=';
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
            url : cntr,
            data : {
                command: 'delete-question',
                id: $(this).attr("data-id")
            }
        });

        $(this).parent().remove();
    });

    $(".close-answer").click(function(){
        $.ajax({
            url : cntr,
            data : {
                command: 'delete-answer',
                id: $(this).attr("data-id")
            }
        });

        $(this).parent().remove();
    });

    $('.rating').on('rating.change', function() {
        $.ajax({
            url : cntr,
            data : {
                command: 'set-rating',
                id: $(this).attr("data-id"),
                mark: $(this).val()
            }
        });
    });
});

