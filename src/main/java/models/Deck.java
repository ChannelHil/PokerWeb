package models;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Channel on 2015-01-09.
 */

public class Deck{

    ArrayList<Card> cards;
    int nCards;

    public Deck() {
    }

    public ArrayList<Hand> createDeck(int nHands){
        nCards = 51;
        cards = new ArrayList<Card>(52);

       int x=0;
       for(Rank rank: Rank.values()){
           for(Suit suit: Suit.values()){
               cards.add(new Card(suit,rank));
           }
       }
       Collections.shuffle(cards);
       return generateHands(nHands);
    }

    private ArrayList<Hand> generateHands(int nHands){
        ArrayList<Hand> hands = null;
        String c1;
        String c2;
        String c3;
        String c4;
        String c5;

        hands = new ArrayList<Hand>(nHands);
        for (int i= 0; i< nHands; i++){

            c1= Rank.nameConverter(cards.get(0).getRank().toString()) + Suit.NameConverter(cards.get(0).getSuit().toString());
            c2= Rank.nameConverter(cards.get(1).getRank().toString()) + Suit.NameConverter(cards.get(1).getSuit().toString());
            c3= Rank.nameConverter(cards.get(2).getRank().toString()) + Suit.NameConverter(cards.get(2).getSuit().toString());
            c4= Rank.nameConverter(cards.get(3).getRank().toString()) + Suit.NameConverter(cards.get(3).getSuit().toString());
            c5= Rank.nameConverter(cards.get(4).getRank().toString()) + Suit.NameConverter(cards.get(4).getSuit().toString());

            for(int j = 0; j<=5 ; j++){
                cards.remove(i);
            }
            hands.add(new Hand(c1,c2,c3,c4,c5));
        }
        return hands;
    }

}
