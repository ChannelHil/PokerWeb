package models;

import javax.persistence.*;

/**
 * Created by Channel on 2015-01-25.
 */
@Entity
public class User_Game extends BaseEntity{


    @ManyToOne (cascade = CascadeType.ALL)
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "User_Game{" +
                "game=" + game +
                ", user=" + user +
                ", result=" + result +
                ", hand=" + hand +
                ", winRound=" + winRound +
                '}';
    }
}
