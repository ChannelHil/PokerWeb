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
import models.User;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import services.IPokerService;
import services.PlayGameService;
import services.PokerService;

import java.util.List;


@Singleton
public class ApplicationController {

    @Inject
    private IPokerService pokerService;

    @Inject
    private PlayGameService playGameService;

    int numberPlayers = 4;

    @FilterWith(SecureFilter.class)
    public Result index() {
        Result result = Results.html();
        setPokerService(pokerService);

        List<Hand> hands = pokerService.dealHands(numberPlayers);
        List<String> users = playGameService.getPlayers(numberPlayers);
        if (users.size() != 4) {
            //Not valid play
        }

        int winner = 0;
        int winHandStrenght = 0;
        boolean firstRun = true;


            for (int i = 0; i < hands.size(); i++) {
                if (firstRun) {
                    winner = i;
                    firstRun = false;
                }else{
                    String n = users.get(i).toString();
                    result.render("user" + i, n);
                }

                List<Card> c = hands.get(i).getCards();

                result.render("cOne" + i, c.get(0).getRank() + "_" + c.get(0).getSuit() + ".png");
                result.render("cTwo" + i, c.get(1).getRank() + "_" + c.get(1).getSuit() + ".png");
                result.render("cThree" + i, c.get(2).getRank() + "_" + c.get(2).getSuit() + ".png");
                result.render("cFour" + i, c.get(3).getRank() + "_" + c.get(3).getSuit() + ".png");
                result.render("cFive" + i, c.get(4).getRank() + "_" + c.get(4).getSuit() + ".png");

                int strength = pokerService.evaluateHand(hands.get(i));
                switch (strength) {
                    case 0:
                        result.render("result" + i, "Nothing");
                        break;
                    case 1:
                        result.render("result" + i, "Pair");
                        break;
                    case 2:
                        result.render("result" + i, "2 Pair");
                        break;
                    case 3:
                        result.render("result" + i, "3 of a Kind");
                        break;
                    case 4:
                        result.render("result" + i, "Straight");
                        break;
                    case 5:
                        result.render("result" + i, "Flush");
                        break;
                    case 6:
                        result.render("result" + i, "Full House");
                        break;
                    case 7:
                        result.render("result" + i, "4 of a Kind");
                        break;
                    case 8:
                        result.render("result" + i, "Straight Flush");
                        break;
                }
                if (strength > winHandStrenght) {
                    winHandStrenght = strength;
                    winner = i;
                }
            }
            //result.render("result" + counter, pokerService.evaluateHand(hand));


        if(winner == 0) {
            result.render("winner", "YOU");
        }else {
            result.render("winner", users.get(winner).toString() + "");
        }
        return result;

    }

    public void setPokerService(IPokerService iPokerService) {
        this.pokerService = iPokerService;
    }
}
