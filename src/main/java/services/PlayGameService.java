package services;

import com.google.inject.Inject;
import models.Game;
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

    public void addWinnerToHistory(Date date){

        Game gameHistory = new Game(date);
        gameRepository.persist(gameHistory);
    }

    public List<User> getPlayers(){
        return userRepository.getPlayers();
    }

    public User findUser(String username){
        Optional<User> user= userRepository.findUserByUName(username);
        return user.get();
    }

    public List<Game> getGameHistory(String username){
        List<Game> game = gameRepository.retrieveHistory(username);
        return game;
    }
}
