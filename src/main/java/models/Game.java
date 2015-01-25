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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    public List<User_Game> user_games;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long getId() {
        return id;
    }

    @Temporal(TemporalType.DATE)
    public Date gameDate;


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

    //ADD player game
    //set game to this check if games !=null

}
