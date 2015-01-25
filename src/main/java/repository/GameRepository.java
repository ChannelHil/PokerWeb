package repository;

import com.google.inject.Inject;
import models.Game;

import java.util.List;

/**
 * Created by Channel on 2015-01-21.
 */
public class GameRepository extends BaseRepository<Game>{

    @Inject
    Game gameHistory;

    public List<Game> retrieveHistory(String username){
        List<Game> gh = (getEntityManager().createQuery("SELECT gh FROM GAME_HISTORY gh WHERE gh.user.username=:username").setParameter("username", username)).getResultList();
        return gh;
    }
}
