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


    @UnitOfWork
    public Optional<User> findUserByUName(String username){
        Optional<User> u = getSingleResult(getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username", username));
        return u;
    }


    List<User> userList = new ArrayList<User>();

    @Inject
    User user;

    /*public Optional findUserByUName(@PathParam("username") String username){
        //userList = user.getUserList();
     if (!userList.isEmpty()){
        for(User u:userList){
            String uname= u.getUsername();
            if(uname.equals(username)){
                return Optional.of(u);
            }
        }
     }
        return Optional.empty();
    }*/

   /* public User addUser(String username, String password, byte[] salt){
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setSalt(salt);
        //userList.add(u);

        return u;
    }
*/

    /*public boolean userExist(String username){
        if (!userList.isEmpty()){
            for(User u:userList){
                String uname= u.getUsername();
                if(uname.equals(username)){
                    return true;
                }
            }
        }
        return false;
    }*/


}
