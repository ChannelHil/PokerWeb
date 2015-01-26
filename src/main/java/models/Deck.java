package models;


import com.google.inject.Inject;
import repository.HandRepository;
import repository.UserRepository;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Channel on 2015-01-09.
 */

public class Deck{

    List<Card> cards;
    List<Hand> hands;

    @Inject
    HandRepository handRepository;

    public Deck() {
        cards = new ArrayList<Card>();
    }

    public List<Hand> createDeck(int number){

        int x=0;
        for(Rank rank: Rank.values()){
            for(Suit suit: Suit.values()){
                cards.add(new Card(suit,rank));
            }
        }
        Collections.shuffle(cards);
        return generateHandsUser(number);
    }
    private List<Hand> generateHandsUser(int number){
        hands = new ArrayList<Hand>();
        List<Card> cardList = cards;

        for (int i= 0; i< number; i++){
            Hand hand= new Hand();

            for (int j=0;j<5;j++) {
                hand.addCard(cardList.get(0));
                cardList.remove(0);
            }
            hands.add(hand);
        }
        return hands;
    }

}
