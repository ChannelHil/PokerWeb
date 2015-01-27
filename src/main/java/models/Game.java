package models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Channel on 2015-01-21.
 */
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @OneToMany( mappedBy = "game" , fetch = FetchType.EAGER)
    public List<User_Game> user_games;

    public Long getId() {
        return id;
    }

    @Temporal(TemporalType.DATE)
    public Date gameDate;

    @Enumerated(EnumType.STRING)
    State state;

    public Game() {
    }

    public Game(Date gameDate) {
        this.gameDate = gameDate;
    }


    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    @Override
    public String toString() {
        return String.valueOf(gameDate);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<User_Game> getUser_games() {
        return user_games;
    }

    public void setUser_games(List<User_Game> user_games) {
        this.user_games = user_games;
    }

    //ADD player game
    //set game to this check if games !=null

}
