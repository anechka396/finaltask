/**
 * Created by Пользователь on 29.04.2016.
 */
$(document).ready(function() {
    $('#show-topics').click(function() {
        $.ajax({
            url : '/Controller',
            data : {
                command: 'all-topics'
            },
            dataType : 'json',
            success : function(responseText) {
                $.each(responseText, function(index, item) { // Iterate over the JSON array.
                    $.each(item, function( index, value ) {
                        alert( index + ": " + value );
                    });
                    $('#ajaxGetUserServletResponse').text(item[0]);
                   // $("<li>").text(item).appendTo($ul);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>.
                });

            }
        });
    });
});

