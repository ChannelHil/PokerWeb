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

    @ManyToOne (fetch = FetchType.LAZY)
    public User user;

    @Enumerated(EnumType.ORDINAL)
    public Result result;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long getId() {
        return id;
    }
    public boolean winRound;

    @Temporal(TemporalType.DATE)
    public Date gameDate;

    public Game_History() {
    }

    public Game_History(User user, Result result, boolean winRound, Date gameDate) {
        this.user = user;
        this.result = result;
        this.winRound = winRound;
        this.gameDate = gameDate;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }

    public boolean isWinRound() {
        return winRound;
    }

    public void setWinRound(boolean winRound) {
        this.winRound = winRound;
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
}
