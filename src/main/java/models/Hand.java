package models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Channel on 2015-01-09.
 */
@Entity
public class Hand{

String name;

    @ManyToMany
    List<Card> cards;

    @ManyToMany
    List<Game_History> game_histories;

    @ManyToOne
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public Hand(String h1,String h2,String h3,String h4,String h5, User user) {
        cards = new ArrayList<Card>();

        cards.add(new Card(h1.substring(1), h1.substring(0, 1)));
        cards.add(new Card(h2.substring(1), h2.substring(0, 1)));
        cards.add(new Card(h3.substring(1), h3.substring(0, 1)));
        cards.add(new Card(h4.substring(1), h4.substring(0, 1)));
        cards.add(new Card(h5.substring(1), h5.substring(0, 1)));
        setCardList(cards);
        this.user=user;

    }

    public void setCardList(List<Card> cardList) {
        this.cards = cardList;
    }
    public List<Card> getCards(){
        return cards;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public List<Game_History> getGame_histories() {
        return game_histories;
    }

    public void setGame_histories(List<Game_History> game_histories) {
        this.game_histories = game_histories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        //return "Hand{" + "cardList=" + cardList + '}';
        return cards.toString();
    }
}
