package services;

import com.google.inject.Inject;
import models.GameHistory;
import models.Result;
import models.User;
import repository.GameRepository;
import repository.UserRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Channel on 2015-01-21.
 */
public class PlayGameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    UserRepository userRepository;

    public void addWinnerToHistory(User user, Result result, boolean won,Date date){
        GameHistory gameHistory = new GameHistory(user,result,won,date);
        gameRepository.persist(gameHistory);
    }

    public List<String> getPlayers(int numberPlayers){
        return userRepository.getPlayers(numberPlayers);
    }
}
