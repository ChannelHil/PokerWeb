package services;

import com.google.inject.Inject;
import models.*;
import repository.GameRepository;
import repository.UserGameRepository;
import repository.UserRepository;

import javax.transaction.Transactional;
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
    UserGameRepository userGameRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    private IPokerService pokerService;

    models.Result resultEnum;

    public PlayGameService() {
        setPokerService(pokerService);
    }

    public void addWinnerToHistory(Date date){

        Game gameHistory = new Game(date);
        //gameRepository.persist(gameHistory);
    }

    public List<User> getPlayers(){
        return userRepository.getPlayers();
    }

    public User findUser(String username){
        Optional<User> user= userRepository.findUserByUName(username);
        return user.get();
    }

    public List<User_Game> getGameHistory(String username){
        List<User_Game> game = userGameRepository.retrieveHistory(username);
        return game;
    }

    //create game
    public Long hostGame(User user){
        User_Game user_game = new User_Game();
        Game game = new Game();
        game.setGameDate(new Date());
        game.setState(State.PENDING);
        user_game.setUser(user);
        user_game.setGame(game);
        userGameRepository.persist(user_game);
        return user_game.getId();
    }

    //join game
    public List<Game> viewGames(){
        List<Game> g = gameRepository.retrieveGames();
        return g;
    }


    public void joinGame(Long id, String username){
        Optional<Game> game = gameRepository.getGameFromId(id);
        User_Game user_game = new User_Game();
        user_game.setGame(game.get());
        user_game.setUser(findUser(username));
        userGameRepository.persist(user_game);
    }

    //get amount of players
    public int countPlayers(Long gameId){
        List<User_Game> user_games = userGameRepository.retrieveGamePlayers(gameId);
        return user_games.size();
    }

    //play game
    public List<User_Game> playGame(Long gameId){
        List<User_Game> user_games = userGameRepository.retrieveGamePlayers(gameId);
        Optional<Game> g = gameRepository.getGameFromId(gameId);
        g.get().setState(State.ACTIVE);
        List<Hand> hands = pokerService.dealHands(user_games.size());
        int winnerIndex=0;
        int highestStrength= 0;

        for(int i=0; i<user_games.size(); i++){
            user_games.get(i).setHand(hands.get(i));
            int strength = pokerService.evaluateHand(hands.get(i));
            if(strength>highestStrength){
                winnerIndex = i;
                highestStrength = strength;
            }
            user_games.get(i).setResult(getStrengthEnum(strength));
            user_games.get(i).setWinRound(false);
            userGameRepository.merge(user_games.get(i));
        }
        user_games.get(winnerIndex).setWinRound(true);
        userGameRepository.merge(user_games.get(winnerIndex));

        return user_games;
    }

    public Result getStrengthEnum(int strength){
        switch (strength) {
            case 0:
                return resultEnum = models.Result.HIGH_CARD;
            case 1:
                return resultEnum = models.Result.PAIR;
            case 2:
                return resultEnum = models.Result.TWO_PAIR;
            case 3:
                return resultEnum = models.Result.THREE_OF_A_KIND;
            case 4:
                return resultEnum = models.Result.STRAIGHT;
            case 5:
                return resultEnum = models.Result.FLUSH;
            case 6:
                return resultEnum = models.Result.FULL_HOUSE;
            case 7:
                return resultEnum = models.Result.FOUR_OF_A_KIND;
            case 8:
                return resultEnum = models.Result.STRAIGHT_FLUSH;
        }
        return resultEnum = models.Result.HIGH_CARD;
    }

    //join
    //retrieve game (game id), create new player game and set user and persist.
    //when start below..

    public void setPokerService(IPokerService iPokerService) {
        this.pokerService = iPokerService;
    }


}
