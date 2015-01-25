package models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Channel on 2015-01-21.
 */
@Entity(name = "GAME_HISTORY")
public class Game_History {

    //@ManyToOne (fetch = FetchType.LAZY)
    //public User user;

    @ManyToMany
    public List<User> user;

    @Enumerated(EnumType.ORDINAL)
    public Result result;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long getId() {
        return id;
    }
    public String winUsername;

    @Temporal(TemporalType.DATE)
    public Date gameDate;




    public Game_History() {
    }

    public Game_History(Result result, String winUsername, Date gameDate, List<User> user) {
        this.result = result;
        this.winUsername = winUsername;
        this.gameDate = gameDate;
        this.user = user;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getWinUsername() {
        return winUsername;
    }

    public void setWinUsername(String winUsername) {
        this.winUsername = winUsername;
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
}
