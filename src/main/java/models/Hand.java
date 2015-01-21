package models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Channel on 2015-01-09.
 */
@Entity
public class Hand extends BaseEntity{

    @ManyToMany
            //(cascade = CascadeType.ALL , mappedBy = hands)
    List<Card> cardList;

    public Hand() {
    }

    public Hand(String h1,String h2,String h3,String h4,String h5) {
        cardList = new ArrayList<Card>();

        cardList.add(new Card(h1.substring(1),h1.substring(0,1)));
        cardList.add(new Card(h2.substring(1),h2.substring(0,1)));
        cardList.add(new Card(h3.substring(1),h3.substring(0,1)));
        cardList.add(new Card(h4.substring(1),h4.substring(0,1)));
        cardList.add(new Card(h5.substring(1),h5.substring(0,1)));


    }

    public List<Card> getCards(){
        return cardList;
    }

    @Override
    public String toString() {
        //return "Hand{" + "cardList=" + cardList + '}';
        return cardList.toString();
    }
}
