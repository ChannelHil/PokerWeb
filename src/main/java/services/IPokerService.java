package services;


import models.Hand;
import models.User;

import java.util.List;

/**
 * Created by Channel on 2015-01-12.
 */
public interface IPokerService {

//TODO
//    Hand dealHand();
    //Add high card
//String evaluateHand(Hand hand);

    List<Hand> dealHands(int number);
    int evaluateHand(Hand hand);
}
