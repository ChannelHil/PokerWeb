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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public Hand(String h1,String h2,String h3,String h4,String h5) {
        cards = new ArrayList<Card>();

        cards.add(new Card(h1.substring(1), h1.substring(0, 1)));
        cards.add(new Card(h2.substring(1), h2.substring(0, 1)));
        cards.add(new Card(h3.substring(1), h3.substring(0, 1)));
        cards.add(new Card(h4.substring(1), h4.substring(0, 1)));
        cards.add(new Card(h5.substring(1), h5.substring(0, 1)));
        setCardList(cards);

    }

    public void setCardList(List<Card> cardList) {
        this.cards = cardList;
    }
    public List<Card> getCards(){
        return cards;
    }

    @Override
    public String toString() {
        //return "Hand{" + "cardList=" + cardList + '}';
        return cards.toString();
    }
}
