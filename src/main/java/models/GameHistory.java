package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Channel on 2015-01-21.
 */
@Entity
public class GameHistory {

    @ManyToOne (fetch = FetchType.LAZY)
    private User users;

    @Enumerated(EnumType.ORDINAL)
    private Result result;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }
   private boolean winRound;

    @Temporal(TemporalType.DATE)
    private Date gameDate;

    public GameHistory() {
    }

    public GameHistory(User users, Result result, boolean winRound, Date gameDate) {
        this.users = users;
        this.result = result;
        this.winRound = winRound;
        this.gameDate = gameDate;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
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
}
