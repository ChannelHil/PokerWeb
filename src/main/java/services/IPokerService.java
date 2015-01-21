package services;


import models.Hand;

import java.util.List;

/**
 * Created by Channel on 2015-01-12.
 */
public interface IPokerService {

//TODO
//    Hand dealHand();
    //Add high card
//String evaluateHand(Hand hand);

    Hand dealHand();
    List<Hand> dealHands(int numberHands);
    int evaluateHand(Hand hand);
}
