package services;

import com.google.inject.Singleton;
import models.Deck;
import models.Hand;
import models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Channel on 2015-01-12.
 */
@Singleton
public class PokerService implements IPokerService {

    private String name = "Channel";

    public     String getName() {
        return name;
    }



    @Override
    public List<Hand> dealHands(List<User> users) {
        Deck deck = new Deck();
        return deck.createDeck(users);
    }

    @Override
    public int evaluateHand(Hand hand) {
        int result;
        if (HandEvaluator.isStraightFlushFunctional(hand)){
            result = 8;
        }else if(HandEvaluator.isFourOfAKindFunctional(hand)){
            result=7;
        }else if (HandEvaluator.isFullHouseFunctional(hand)) {
            result=6;
        }else if(HandEvaluator.isFlushFunctional(hand)){
            result=5;
        }else if(HandEvaluator.isStraight(hand)){
            result=4;
        }else if(HandEvaluator.isThreeOfAKind(hand)){
            result=3;
        }else if (HandEvaluator.isThreeOfAKindFunctional(hand)){
            result=2;
        }else if (HandEvaluator.isPairFunctional(hand)){
            result=1;
        }else{
            result =0;
        }


        return result;
    }
}
