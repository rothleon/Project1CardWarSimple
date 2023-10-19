package CIS3334.leonroth.project1cardwarsimple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeckResponse {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("deck_id")
    @Expose
    private String deck_id;

    @SerializedName("shuffled")
    @Expose
    private boolean shuffled;

    @SerializedName("remaining")
    @Expose
    private Integer remaining;

    @SerializedName("cards")
    @Expose
    private List<Card> cards;

}
