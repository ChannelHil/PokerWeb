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
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.session.Session;
import services.AuthenticationService;
import services.IPokerService;
import services.PlayGameService;
import services.PokerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Singleton
public class ApplicationController {

    @Inject
    private IPokerService pokerService;

    @Inject
    private PlayGameService playGameService;

    @Inject
    AuthenticationService authenticationService;

    models.Result resultEnum;


    int numberPlayers = 4;
    int winner = 0;


    @FilterWith(SecureFilter.class)
    public Result index(Context context) {
        Result result = Results.html();
        setPokerService(pokerService);
        User u = new User();

        Session session = context.getSession();
        String username = session.get("login");

        User loggedInUser=u = playGameService.findUser(username);
        List<User> users = new ArrayList<User>();
        users.add(u);
        users.add(playGameService.getPlayers().get(0));
        users.add(playGameService.getPlayers().get(1));
        users.add(playGameService.getPlayers().get(2));

        List<Hand> hands = pokerService.dealHands(users);

       /* List<Hand> hands = pokerService.dealHands(numberPlayers);
        if (users.size() != 4) {
            //Not valid play
        }
*/
        int winHandStrenght = 0;
        boolean firstRun = true;


        //result.render("users", users);
        result.render("hands", hands);

        for (int i = 0; i < hands.size(); i++) {
            if (firstRun) {
                winner = i;
                firstRun = false;
            } else {
                String n = users.get(i).getUsername().toString();
                result.render("user" + i, n);
            }

            List<Card> c = hands.get(i).getCards();

            result.render("cOne" + i, c.get(0).getRank() + "_" + c.get(0).getSuit() + ".png");
            result.render("cTwo" + i, c.get(1).getRank() + "_" + c.get(1).getSuit() + ".png");
            result.render("cThree" + i, c.get(2).getRank() + "_" + c.get(2).getSuit() + ".png");
            result.render("cFour" + i, c.get(3).getRank() + "_" + c.get(3).getSuit() + ".png");
            result.render("cFive" + i, c.get(4).getRank() + "_" + c.get(4).getSuit() + ".png");


            //TODO move to controller
            int strength = pokerService.evaluateHand(hands.get(i));
            switch (strength) {
                case 0:
                    result.render("result" + i, "Nothing");
                    resultEnum = models.Result.HIGH_CARD;
                    break;
                case 1:
                    result.render("result" + i, "Pair");
                    resultEnum = models.Result.PAIR;
                    break;
                case 2:
                    result.render("result" + i, "2 Pair");
                    resultEnum = models.Result.TWO_PAIR;
                    break;
                case 3:
                    result.render("result" + i, "3 of a Kind");
                    resultEnum = models.Result.THREE_OF_A_KIND;
                    break;
                case 4:
                    result.render("result" + i, "Straight");
                    resultEnum = models.Result.STRAIGHT;
                    break;
                case 5:
                    result.render("result" + i, "Flush");
                    resultEnum = models.Result.FLUSH;
                    break;
                case 6:
                    result.render("result" + i, "Full House");
                    resultEnum = models.Result.FULL_HOUSE;
                    break;
                case 7:
                    result.render("result" + i, "4 of a Kind");
                    resultEnum = models.Result.FOUR_OF_A_KIND;
                    break;
                case 8:
                    result.render("result" + i, "Straight Flush");
                    resultEnum = models.Result.STRAIGHT_FLUSH;
                    break;
            }
            if (strength > winHandStrenght) {
                winHandStrenght = strength;
                winner = i;
            }
        }
        //result.render("result" + counter, pokerService.evaluateHand(hand));

        //result.render("hands", cardName);

        if (winner == 0) {
            u = playGameService.findUser(username);
            result.render("winner", "YOU");
        } else {
            u = playGameService.findUser(users.get(winner).getUsername());
            result.render("winner", users.get(winner).getUsername().toString() + "");
        }

        //TODO add to table
        users.remove(0);
        //playGameService.addWinnerToHistory(users,users.get(winner).getUsername().toString(),resultEnum, new Date());

        return result;

    }

    public void setPokerService(IPokerService iPokerService) {
        this.pokerService = iPokerService;
    }

    @FilterWith(SecureFilter.class)
    public Result play(Context context) {
        Result result = Results.html();
        return result;
    }
}
