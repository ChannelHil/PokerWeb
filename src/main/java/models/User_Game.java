package models;

import javax.persistence.*;

/**
 * Created by Channel on 2015-01-25.
 */
@Entity
public class User_Game extends BaseEntity{


    @ManyToOne
    Game game;

    @ManyToOne
    User user;

    @Enumerated(EnumType.STRING)
    Result result;

    @OneToOne
    Hand hand;

    boolean winRound;

    public boolean isWinRound() {
        return winRound;
    }

    public void setWinRound(boolean winRound) {
        this.winRound = winRound;
    }
}
