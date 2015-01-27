$(document).ready(function()
{
    function viewPlayers(users){
        $( "#players" ).html("");
        $.each(users, function(k,user)
        {
            $( "#players" ).append('<li>' + user.username + '</li>');
        });
    };

      function updateGame(){
                $.ajax(
                {
                    type: "GET",
                    url: "/gameUpdate"
                }).done(function(users)
                {
                    viewPlayers(users);
                    updateGame();
                });
            };
    updateGame();
});