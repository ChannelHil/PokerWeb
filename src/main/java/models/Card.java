package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Channel on 2015-01-09.
 */
@Entity
public class Card implements Serializable{

    @EmbeddedId
    Rank_Suit rank_suit;

    @ManyToMany
    List<Hand> hands;


    public Card() {
    }

    public Card(Suit suit, Rank rank) {
        rank_suit = new Rank_Suit(suit,rank);
    }

    public Card(String suit, String rank) {
        rank_suit = new Rank_Suit();
        rank_suit.setSuit(determineSuit(suit));
        rank_suit.setRank(determineRank(rank));
    }
    public Rank determineRank(String rank){
        for (Rank r: Rank.values()){
            if (r.equalRank(rank)){
                return r;
            }

        }
        return null;
    }
    public Suit determineSuit(String suit){
        for (Suit s: Suit.values()){
            if (s.equalSuit(suit)){
                return s;
            }

        }
        return null;
    }

    public Suit getSuit() {
        if(rank_suit==null){
            return null;
        }
        return rank_suit.getSuit();
    }

    public Rank getRank() {
        if(rank_suit==null){
            return null;
        }
        return rank_suit.getRank();
    }


    @Override
    public String toString() {
        return "Card{" +
                "rank_suit=" + rank_suit +
                ", hands=" + hands +
                '}';
    }
}
