package models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Channel on 2015-01-21.
 */
@Entity
public class GameHistory extends BaseEntity {

    @OneToMany
    private List<User> users;

    @Enumerated(EnumType.ORDINAL)
    private Result result;

   // private boolean winRound;

    //@Temporal(TemporalType.DATE)
    //private Date gameDate;

    public GameHistory() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
/*
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
    }*/
}
