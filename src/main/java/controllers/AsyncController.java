package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Game;
import models.User;
import models.User_Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import services.PlayGameService;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Channel on 2015-01-27.
 */
@Singleton
public class AsyncController {

    @Inject
    private PlayGameService playGameService;

    private volatile ConcurrentHashMap<Long, Instant> updateMap = new ConcurrentHashMap<>();
    private volatile Instant updateGames;

    public void updateGameResult(long id) {
        Instant instant = Instant.now();
        updateMap.put(id, instant);
    }

    public synchronized void updateGamesList() {
        updateGames = Instant.now();
    }

    public Result gameUpdate(Context context) throws InterruptedException {
        Instant instant = Instant.now();
        String gameIdString = context.getSession().get("gameId");
        Long gameId= Long.parseLong(gameIdString);

        while (updateMap.get(gameId) == null ||updateMap.get(gameId).isBefore(instant)) {
            Thread.sleep(100);
        }
        Game game = playGameService.getPlayersGame(gameId);

        List<User_Game> user_games = playGameService.getGamePlayers(gameId);

        if(user_games!=null) {
            List<User> users = new ArrayList<User>();
            for (User_Game user_game : user_games) {

                users.add(user_game.getUser());
            }
            //result.render("users", users);
            return Results.json().render(users);
        }

        return Results.notFound();
    }
}
