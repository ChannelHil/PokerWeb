package services;

import com.google.inject.Inject;
import models.Game_History;
import models.Result;
import models.User;
import repository.GameRepository;
import repository.UserRepository;

import java.util.ArrayList;
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

    public void addWinnerToHistory(List<User> users, String userWinner, Result result,Date date){

        Game_History gameHistory = new Game_History(result,userWinner,date,users);
        gameRepository.persist(gameHistory);
    }

    public List<User> getPlayers(){
        return userRepository.getPlayers();
    }

    public User findUser(String username){
        Optional<User> user= userRepository.findUserByUName(username);
        return user.get();
    }

    public List<Game_History> getGameHistory(String username){
        List<Game_History> game_history= gameRepository.retrieveHistory(username);
        return game_history;
    }
}
