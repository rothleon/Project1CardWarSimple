package CIS3334.leonroth.project1cardwarsimple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

public class Card  {
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("suit")
    @Expose
    private String suit;

    @SerializedName("code")
    @Expose
    private String code;

    public Integer getRank() {
        this.rank = findRank(value);
        return this.rank;
    }

    /*public void setRank(Integer rank) {
        this.rank = rank;
    }*/

    private Integer rank;

    static final String RANKING[] = {"","","2","3","4","5","6","7","8","9","10","JACK","QUEEN","KING","ACE"};

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
