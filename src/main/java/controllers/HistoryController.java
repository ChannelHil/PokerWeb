package controllers;

import com.google.inject.Inject;
import filters.SecureFilter;
import models.Game;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;
import services.PlayGameService;

import java.util.List;

/**
 * Created by Channel on 2015-01-21.
 */
public class HistoryController {

    @Inject
    private PlayGameService playGameService;

    @FilterWith(SecureFilter.class)
    public Result history(Context context) {
        Result result = Results.html();
        Session session = context.getSession();
        String username = session.get("login");
        List<Game> game_histories = playGameService.getGameHistory(username);
        /*game_histories.clear();
        Game_History game = new Game_History();
        game.id = 12L;
        game_histories.add(game);*/
        result.render("game_histories", game_histories);

        return result;
    }

}
