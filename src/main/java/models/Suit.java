package models;



/**
 * Created by Channel on 2015-01-09.
 */
public enum Suit {
    SPADES ("S"),
    HEARTS ("H"),
    DIAMONDS ("D"),
    CLUBS ("C");

    private final String suitName;

    private Suit(String sName) {
        suitName = sName;
    }

    public boolean equalSuit(String suit){
        if (suit==null || suit.isEmpty() ){
            return false;
        }

        if(suitName.equals(suit)){
            return true;
        }else{
            return false;
        }
    }

    public static String NameConverter(String name){
        String convert="";
        switch (Suit.valueOf(name)){
            case DIAMONDS:  convert = "D";
                break;
            case SPADES:  convert = "S";
                break;
            case HEARTS:  convert = "H";
                break;
            case CLUBS:  convert = "C";
                break;

        }
        return convert;
    }
}
