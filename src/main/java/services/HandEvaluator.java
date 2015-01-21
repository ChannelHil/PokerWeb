package services;


import models.Card;
import models.Hand;
import models.Rank;
import models.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Channel on 2015-01-09.
 */
public class HandEvaluator {

    public static boolean isStraightFlushFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        //ispresent to test for null
        boolean flush = hand.getCards().stream().allMatch(c->c.getSuit()==hand.getCards().get(0).getSuit());
        boolean straight = ranks.stream().mapToInt(r->r.ordinal()).max().getAsInt() -
                ranks.stream().mapToInt(r->r.ordinal()).min().getAsInt()==4;

        if(flush && straight) {
            return true;
        }else{
            return false;
        }
    }

    public static boolean isFlushFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        //ispresent to test for null
        boolean flush = hand.getCards().stream().allMatch(c->c.getSuit()==hand.getCards().get(0).getSuit());

        return flush;
    }

    public static boolean isStraightFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        boolean straight = ranks.stream().mapToInt(r->r.ordinal()).max().getAsInt() -
                ranks.stream().mapToInt(r->r.ordinal()).min().getAsInt()==4;

        return straight;
    }
    public static boolean isPairFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        return ranks.stream().distinct().count()==4;
    }
    public static boolean isTwoPairFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        if (ranks.stream().distinct().count()==2){
            long countMatches = ranks.stream().filter(c -> c == ranks.get(0)).count();
            long countM = ranks.stream().filter(c -> c == ranks.get(1)).count();
            return (countMatches==2) || (countM==2);
        }

        return false;
    }

    public static boolean isThreeOfAKindFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        return ranks.stream().distinct().count()==3;
    }
    public static boolean isFourOfAKindFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        if (ranks.stream().distinct().count()==2){
            long countMatches = ranks.stream().filter(c -> c == ranks.get(0)).count();
            return (countMatches==1) || (countMatches==4);
        }

        return false;
    }

    public static boolean isFullHouseFunctional(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c->c.getRank()).collect(Collectors.toList());
        if (ranks.stream().distinct().count()==2){
            long countMatches = ranks.stream().filter(c -> c == ranks.get(0)).count();
            return (countMatches==2) || (countMatches==3);
        }

        return false;
    }

    public static boolean isStraightFlush(Hand hand) {
        //sort cards
        //hand.getCards
        //Card card1 = hand.toString();

        Collections.sort(hand.getCards(), new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {

                return o1.getRank().compareTo(o2.getRank());
            }
        });

        //hand.getCards().stream().sorted(o1,o2) -> o1.getRank().compareTo(02.getRank());

        Rank prevRank = null;
        Suit prevSuit = null;
        for (Card card : hand.getCards()) {
            if (prevRank != null && card.getRank().ordinal() != prevRank.ordinal() + 1) {
                return false;
            }
            if (prevSuit != null && card.getSuit() != prevSuit) {
                return false;
            }
            prevRank = card.getRank();
            prevSuit = card.getSuit();


        }

        return true;
    }

    public static boolean isFlush(Hand hand) {
        Suit suit = null;
        Suit toCompare = null;
        boolean firstEncounter = true;
        for (Card card : hand.getCards()){
            if(firstEncounter) {
                suit = card.getSuit();
                firstEncounter = false;
            }
            toCompare = card.getSuit();
            if (suit != toCompare){
                return false;
            }
        }
        return true;
    }

    public static boolean isStraight(Hand hand) {
        Collections.sort(hand.getCards(), new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {

                return o1.getRank().compareTo(o2.getRank());
            }
        });

        Rank prevRank = null;
        for (Card card : hand.getCards()) {
            if (prevRank != null && card.getRank().ordinal() != prevRank.ordinal() + 1) {
                return false;
            }
            prevRank = card.getRank();
        }

        return true;
    }

    public static boolean isPair(Hand hand) {
        Rank rank = null;
        Rank toCompare = null;
        int count = 0;
        int nCards=0;
        List<Card> cardList = new ArrayList<Card>();
        cardList = hand.getCards();

        //rank = hand.getCards().get(0).getRank();
        for(int i=0; i<cardList.size(); i++){
            rank=cardList.get(i).getRank();
            for (int j=0; j<cardList.size(); j++) {
                toCompare = cardList.get(j).getRank();
                if (i!=j && rank == toCompare) {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean isThreeOfAKind(Hand hand) {
        Rank rank = null;
        Rank toCompare = null;
        int count = 0;
        int nCards=0;
        List<Card> cardList = new ArrayList<Card>();
        cardList = hand.getCards();

        //rank = hand.getCards().get(0).getRank();
        for(int i=0; i<cardList.size(); i++){
            rank=cardList.get(i).getRank();
            for (int j=0; j<cardList.size(); j++) {
                toCompare = cardList.get(j).getRank();
                for (int k=0; k<cardList.size(); k++) {

                if (i!=j && i!=k && k!=j && rank == toCompare && toCompare == cardList.get(k).getRank()) {
                    return true;
                }
                }

            }
        }
        return false;

    }

    public static boolean isFourOfAKind(Hand hand) {
        Rank rank = null;
        Rank toCompare = null;
        int count = 0;
        int nCards=0;
        List<Card> cardList = new ArrayList<Card>();
        cardList = hand.getCards();


        //rank = hand.getCards().get(0).getRank();
        for(int i=0; i<cardList.size(); i++){
            rank=cardList.get(i).getRank();
            for (int j=0; j<cardList.size(); j++) {
                toCompare = cardList.get(j).getRank();
                if (i!=j && rank == toCompare) {
                    count ++;
                }
            }
        }
        //TODO change
        if(count == 12){
            return true;
        }
        return false;

    }
    private static int countPair(Hand hand) {
        Rank rank = null;
        Rank toCompare = null;
        int count = 0;
        int nCards=0;
        List<Card> cardList = new ArrayList<Card>();
        cardList = hand.getCards();

        //rank = hand.getCards().get(0).getRank();
        for(int i=0; i<cardList.size(); i++){
            rank=cardList.get(i).getRank();
            count=0;
            for (int j=0; j<cardList.size(); j++) {

                toCompare = cardList.get(j).getRank();
                if (i!=j && rank == toCompare) {
                    count++;
                }
            }
        }
        return count;

    }

    public static boolean isFullHouse(Hand hand) {
       boolean isThreeOfAKind = isThreeOfAKind(hand);
        boolean isPair = isPair(hand);
        int count=countPair(hand);

        if (isThreeOfAKind ){
            if(count==1) {
                return true;
            }
            return false;
        }
        else{
            return false;
        }

    }

}