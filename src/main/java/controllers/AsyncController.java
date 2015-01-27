package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import services.PlayGameService;


import java.time.Instant;
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

    public void updateGamesList() {
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
        if(game!=null){
            return Results.json().render(game);
        }
        return Results.notFound();
    }
}
