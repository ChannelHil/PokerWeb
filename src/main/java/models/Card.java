package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Channel on 2015-01-09.
 */
@Entity
public class Card{

    @ManyToMany
    private List<Hand> hands;

    @Enumerated(EnumType.STRING)
    private Suit suit;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }
    public Card() {
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(String suit, String rank) {
        this.suit=determineSuit(suit);
        this.rank=determineRank(rank);
    }
    private Rank determineRank(String rank){
        for (Rank r: Rank.values()){
            if (r.equalRank(rank)){
                return r;
            }

        }
        return null;
    }
    private Suit determineSuit(String suit){
        for (Suit s: Suit.values()){
            if (s.equalSuit(suit)){
                return s;
            }

        }
        return null;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
