package services;

import com.google.inject.Inject;
import models.Game_History;
import models.Result;
import models.User;
import repository.GameRepository;
import repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Channel on 2015-01-21.
 */
public class PlayGameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    UserRepository userRepository;

    public void addWinnerToHistory(User user, Result result, boolean won,Date date){
        Game_History gameHistory = new Game_History(user,result,won,date);
        gameRepository.persist(gameHistory);
    }

    public List<String> getPlayers(int numberPlayers){
        return userRepository.getPlayers(numberPlayers);
    }

    public User findUser(String username){
        Optional<User> user= userRepository.findUserByUName(username);
        return user.get();
    }
}
