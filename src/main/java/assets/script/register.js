$(document).ready(function()
{
$('#login').keyup(function()
	{
		$('#resultName').html(userExist($('#login').val()))
	})

function userExist(login)
	{

    		$.ajax({
            		url: "/user/" + $('#login').val()
                }).done(function(data) {

            		if (data.exists){
            		    $('#resultName').text("User exists");
            		}else{
            		$('#resultName').text("");
            		    }
            	});

}

});
