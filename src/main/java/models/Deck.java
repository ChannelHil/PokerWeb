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

    public List<Hand> createDeck(List<User> users){

        int x=0;
        for(Rank rank: Rank.values()){
            for(Suit suit: Suit.values()){
                cards.add(new Card(suit,rank));
            }
        }
        Collections.shuffle(cards);
        return generateHandsUser(users);
    }
    private List<Hand> generateHandsUser(List<User> users){
        String c1;
        String c2;
        String c3;
        String c4;
        String c5;

        hands = new ArrayList<Hand>();
        List<Card> cardList = cards;

        for (int i= 0; i< users.size(); i++){

            c1= Rank.nameConverter(cardList.get(0).getRank().toString()) + Suit.NameConverter(cardList.get(0).getSuit().toString());
            c2= Rank.nameConverter(cardList.get(1).getRank().toString()) + Suit.NameConverter(cardList.get(1).getSuit().toString());
            c3= Rank.nameConverter(cardList.get(2).getRank().toString()) + Suit.NameConverter(cardList.get(2).getSuit().toString());
            c4= Rank.nameConverter(cardList.get(3).getRank().toString()) + Suit.NameConverter(cardList.get(3).getSuit().toString());
            c5= Rank.nameConverter(cardList.get(4).getRank().toString()) + Suit.NameConverter(cardList.get(4).getSuit().toString());

            for(int j = 0; j<=5 ; j++){
                cardList.remove(j);
            }
            Hand h = new Hand(c1,c2,c3,c4,c5,users.get(i));
            handRepository.persist(h);
            hands.add(h);
        }
        return hands;
    }

}
