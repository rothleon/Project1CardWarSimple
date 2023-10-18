package CIS3334.leonroth.project1cardwarsimple;


import java.util.Arrays;


public class Card {

    private String image;
    private String value;
    private String suit;
    private String code;
    private Integer rank;

    static final String RANKING[] = {"2","3","4","5","6","7","8","9","0","JACK","QUEEN","KING","ACE"};

    public Card(String image, String value, String suit, String code) {
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.code = code;
        this.rank = findRank(value);
    }

    private Integer findRank(String value){
        return Arrays.asList(RANKING).indexOf(value);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
