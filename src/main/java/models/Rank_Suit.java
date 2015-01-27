package models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by Channel on 2015-01-25.
 */
public class Rank_Suit implements Serializable{

    @Enumerated(EnumType.STRING)
    private Suit suit;

    @Enumerated(EnumType.ORDINAL)
    private Rank rank;

    public Rank_Suit() {
    }

    public Rank_Suit(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rank_Suit)) return false;

        Rank_Suit that = (Rank_Suit) o;

        if (rank != that.rank) return false;
        if (suit != that.suit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rank_Suit{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
