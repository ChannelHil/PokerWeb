package repository;

import com.google.inject.Inject;
import models.Game;
import models.User;
import models.User_Game;

import java.util.List;
import java.util.Optional;

/**
 * Created by Channel on 2015-01-21.
 */
public class GameRepository extends BaseRepository<Game>{

    @Inject
    Game game;

    //todo
    public List<Game> retrieveHistory(String username){
        List<Game> gh = (getEntityManager().createQuery("SELECT gh FROM Game gh WHERE gh.user.username=:username").setParameter("username", username)).getResultList();
        return gh;
    }

    public Optional<Game> getGameFromId(Long id){
        Optional<Game> game = getSingleResult(getEntityManager().createQuery("SELECT g FROM Game g WHERE g.id = :id").setParameter("id", id));
        return game;
    }
    public List<Game> retrieveGames(){
        List<Game> gh = (getEntityManager().createQuery("SELECT gh FROM Game gh WHERE gh.state='PENDING'")).getResultList();
        return gh;
    }

}
