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

    public List<Game_History> retrieveHistory(String username){
        List<Game_History> gh = (getEntityManager().createQuery("SELECT gh FROM GAME_HISTORY gh WHERE gh.user.username=:username").setParameter("username", username)).getResultList();
        return gh;
    }
}
