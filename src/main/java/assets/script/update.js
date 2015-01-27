$(document).ready(function()
{
    function viewPlayers(game){
        $( "#players" ).html("");
        $.each(game.user_games, function(k,user_game)
        {
            $( "#players" ).append('<li id="list">' + user_game.user.username+ '</li>');
        });
    };

      function updateGame(){
                $.ajax(
                {
                    type: "GET",
                    url: "/gameUpdate"
                }).done(function(game)
                {
                    if (game.status === "FINISHED")
                    {

                    }
                    viewPlayers(game);
                    updateGame();
                });
            };
    updateGame();
});