package repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.User;
import ninja.jpa.UnitOfWork;
import ninja.params.PathParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Channel on 2015-01-16.
 */
@Singleton
public class UserRepository extends BaseRepository<User>{

    @Inject
    User user;

    @UnitOfWork
    public Optional<User> findUserByUName(String username){
        Optional<User> u = getSingleResult(getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username", username));
        return u;
    }

    @UnitOfWork
    public List<String> getPlayers(int numberPlayers) {
        //return getEntityManager().createQuery("SELECT u.username FROM User u LIMIT " + numberPlayers).getResultList();
        return getEntityManager().createQuery("SELECT username FROM User").getResultList();
    }

}
