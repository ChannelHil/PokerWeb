package services;

import models.Card;
import models.Hand;

import java.util.List;

/**
 * Created by Channel on 2015-01-12.
 */
public class MatchCards {

    public void matchCardToImage(Hand hand){
        List<Card> c = hand.getCards();
        for(Card card:c){
            card.getRank();
            card.getSuit();
        }
    }

}
