package services;

import com.google.inject.Singleton;
import models.*;

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
    public List<Hand> dealHands(int number) {
        Deck deck = new Deck();
        return deck.createDeck(number);
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




    //join
    //retrieve game (game id), create new player game and set user and persist.
    //when start below..


    //new
    //create new game with user (host and persist)
    // when start game takes id of game retrieve game, deal hands and the result and persist
    // playergame
    //sethands and users and result and finally add to game
}
