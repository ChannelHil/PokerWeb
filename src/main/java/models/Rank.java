package models;

/**
 * Created by Channel on 2015-01-09.
 */
public enum Rank {
    ACE ("A"),
    TWO ("2"),
    THREE ("3"),
    FOUR ("4"),
    FIVE ("5"),
    SIX ("6"),
    SEVEN ("7"),
    EIGHT ("8"),
    NINE ("9"),
    TEN ("0"),
    JACK ("J"),
    QUEEN ("Q"),
    KING ("K");

    private final String rankName;

    private Rank(String rName) {
        rankName = rName;
    }

    public boolean equalRank(String rank){
        if (rank==null || rank.isEmpty() ){
            return false;
        }

        if(rankName.equals(rank)){
            return true;
        }else{
            return false;
        }
    }

    public static String nameConverter(String name){
        String convert="";
        switch (Rank.valueOf(name)){
            case ACE:  convert = "A";
                break;
            case TWO:  convert = "2";
                break;
            case THREE:  convert = "3";
                break;
            case FOUR:  convert = "4";
                break;
            case FIVE:  convert = "5";
                break;
            case SIX:  convert = "6";
                break;
            case SEVEN:  convert = "7";
                break;
            case EIGHT:  convert = "8";
                break;
            case NINE:  convert = "9";
                break;
            case TEN:  convert = "0";
                break;
            case KING:  convert = "K";
                break;
            case QUEEN:  convert = "Q";
                break;
            case JACK:  convert = "J";
                break;
        }
        return convert;
    }
}
