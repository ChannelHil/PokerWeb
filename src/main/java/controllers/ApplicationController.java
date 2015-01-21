/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import com.google.inject.Inject;
import filters.SecureFilter;
import models.Card;
import models.Hand;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import services.IPokerService;
import services.PokerService;

import java.util.List;


@Singleton
public class ApplicationController {

    @Inject
    private IPokerService pokerService;

    @FilterWith(SecureFilter.class)
    public Result index() {
        Result result = Results.html();
        //PokerService pokerService = new PokerService();

        //result.render("name", pokerService.getName());
        setPokerService(pokerService);
        List<Hand> hands = pokerService.dealHands(4);
        int counter = 1;
        int size = hands.size();
        int winner=0;
        int winHandStrenght=0;
        boolean firstRun = true;
        for(Hand hand:hands){
            if(firstRun) {
                winner = counter;
                firstRun = false;
            }

            List<Card> c = hand.getCards();
            result.render("user" + counter, counter);

            result.render("cOne" + counter, c.get(0).getRank() + "_" + c.get(0).getSuit() + ".png");
            result.render("cTwo" + counter, c.get(1).getRank() + "_" + c.get(1).getSuit()+ ".png");
            result.render("cThree" + counter, c.get(2).getRank() + "_" + c.get(2).getSuit()+ ".png");
            result.render("cFour" + counter, c.get(3).getRank() + "_" + c.get(3).getSuit()+ ".png");
            result.render("cFive" + counter, c.get(4).getRank() + "_" + c.get(4).getSuit()+ ".png");

            int strength = pokerService.evaluateHand(hand);
            switch (strength){
                case 0: result.render("result" + counter, "Nothing");
                    break;
                case 1: result.render("result" + counter, "Pair");
                    break;
                case 2: result.render("result" + counter, "2 Pair");
                    break;
                case 3: result.render("result" + counter, "3 of a Kind");
                    break;
                case 4: result.render("result" + counter, "Straight");
                    break;
                case 5: result.render("result" + counter, "Flush");
                    break;
                case 6: result.render("result" + counter, "Full House");
                    break;
                case 7: result.render("result" + counter, "4 of a Kind");
                    break;
                case 8: result.render("result" + counter, "Straight Flush");
                    break;
            }
            //result.render("result" + counter, pokerService.evaluateHand(hand));

            if(strength>winHandStrenght){
                winHandStrenght=strength;
                winner=counter;
            }
            counter ++;
        }
        result.render("winner", winner + "");

        /*
        Hand hand = pokerService.dealHand();

        List<Card> c = hand.getCards();
        String cOne =c.get(0).getRank() + "_" + c.get(0).getSuit() + ".png";
        String cTwo =c.get(1).getRank() + "_" + c.get(1).getSuit()+ ".png";
        String cThree =c.get(2).getRank() + "_" + c.get(2).getSuit()+ ".png";
        String cFour =c.get(3).getRank() + "_" + c.get(3).getSuit()+ ".png";
        String cFive =c.get(4).getRank() + "_" + c.get(4).getSuit()+ ".png";

        result.render("cOne", cOne);
        result.render("cTwo", cTwo);
        result.render("cThree", cThree);
        result.render("cFour", cFour);
        result.render("cFive", cFive);

        //result.render("result", "tt");
*/

        return result;

    }

    public void setPokerService(IPokerService iPokerService){
        this.pokerService=iPokerService;
    }
}
