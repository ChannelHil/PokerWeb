package repository;

import com.google.inject.Inject;
import models.Game_History;
import models.Hand;
import models.Result;
import models.User;
import ninja.jpa.UnitOfWork;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Channel on 2015-01-21.
 */
public class GameRepository extends BaseRepository<Game_History>{

    @Inject
    Game_History gameHistory;

    public void insertHistory(Result result, boolean winround, Date gamedate){
        //getEntityManager().createQuery("INSERT INTO GAME_HISTORY (RESULT, WINROUND,GAMEDATE) VALUES ('result',winround','gamedate'));
    }

}
