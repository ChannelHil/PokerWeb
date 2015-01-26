package repository;

import com.google.inject.Inject;
import models.Game;
import models.User_Game;

import java.util.List;

/**
 * Created by Channel on 2015-01-26.
 */
public class UserGameRepository extends BaseRepository<User_Game>{

    @Inject
    User_Game user_game;

    public List<User_Game> retrieveGamePlayers(Long id){
        List<User_Game> game = (getEntityManager().createQuery("SELECT ug FROM User_Game ug WHERE ug.game.id = :id AND ").setParameter("id", id)).getResultList();
        return game;
    }

    public List<User_Game> retrieveHistory(String username){
        List<User_Game> gh = (getEntityManager().createQuery("SELECT gh FROM User_Game gh WHERE gh.user.username=:username").setParameter("username", username)).getResultList();
        return gh;
    }
}
