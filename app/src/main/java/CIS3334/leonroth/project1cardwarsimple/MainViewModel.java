package CIS3334.leonroth.project1cardwarsimple;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel {

    private Card playerCard;
    private Card opponentCard;

    private List<Card> playerCardList = new ArrayList<>();

    private List<Card> opponentCardList = new ArrayList<>();

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    private String deck_id;



    final static String BASEURL = "https://www.deckofcardsapi.com/api/deck/";
    private Retrofit retrofit;

    private DeckOfCardsService deckOfCardsService;

    public MainViewModel (Application application) {
        super(application);
        connect();
        newGame();
    }

    public String[] onButtonPlayClick(){
        String[] onButtonOutput = new String[3];

        pullTopCard(playerCardList);
        pullTopCard(opponentCardList);

        onButtonOutput[0] = String.valueOf(compareCards());
        onButtonOutput[1] = playerCard.getImage();
        onButtonOutput[2] = opponentCard.getImage();

        if(compareCards() == 1){
            cardToList(playerCardList,playerCard);
            cardToList(playerCardList,opponentCard);
            playerCard = null;
            opponentCard = null;
        }
        else if(compareCards() == -1){
            cardToList(opponentCardList,playerCard);
            cardToList(opponentCardList,opponentCard);
            playerCard = null;
            opponentCard = null;
        }
        else if(compareCards() == 0){
            cardToList(playerCardList,playerCard);
            cardToList(opponentCardList,opponentCard);
            playerCard = null;
            opponentCard = null;
        }

        return onButtonOutput;

    }

    public void connect() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        if (deckOfCardsService == null) {
            deckOfCardsService = retrofit.create(DeckOfCardsService.class);
        }
    }

    public String getNewDeck(){
        Call<DeckResponse> call = deckOfCardsService.newDeck();
        call.enqueue(new Callback<DeckResponse>() {
            @Override
            public void onResponse(Call<DeckResponse> call, Response<DeckResponse> response) {
                if (response.isSuccessful()) {
                    DeckResponse modal = response.body();
                    deck_id = modal.getDeck_id();
                    Log.d("3334",deck_id);
                }
            }

            @Override
            public void onFailure(Call<DeckResponse> call, Throwable t) {
            }
        });

    return deck_id;
    }


    public void fillOneCardList(List<Card> cardList ){
        //cardList = deckOfCardsService.drawHalfDeck(deck_id);
        Call<DeckResponse> call = deckOfCardsService.drawHalfDeck(deck_id);

        Log.d("3334", "Looking for card list");


        if(cardList == playerCardList && playerCardList.isEmpty()) {
            //Call<DeckResponse> call = deckOfCardsService.drawHalfDeck(deck_id);
            call.enqueue(new Callback<DeckResponse>() {
                @Override
                public void onResponse(Call<DeckResponse> call, Response<DeckResponse> response) {
                    if (response.isSuccessful()) {
                        DeckResponse modal = response.body();
                        Log.d("3334", modal.getCards().toString());
                        //playerCardList.clear();
                        playerCardList = (List<Card>) ((ArrayList<Card>) modal.getCards()).clone();
                        Log.d("3334", "Player Card List Filled " + playerCardList.size());
                        Log.d("3334", modal.getRemaining() + " left in deck");
                    }
                }

                @Override
                public void onFailure(Call<DeckResponse> call, Throwable t) {
                    Log.d("3334", "List Fill Failed");
                }
            });
        }
        else if(opponentCardList.isEmpty()){
            //Call<DeckResponse> call = deckOfCardsService.drawHalfDeck(deck_id);
            call.enqueue(new Callback<DeckResponse>() {
                @Override
                public void onResponse(Call<DeckResponse> call, Response<DeckResponse> response) {
                    if (response.isSuccessful()) {
                        DeckResponse modal = response.body();
                        //opponentCardList.clear();
                        opponentCardList = (List<Card>) ((ArrayList<Card>) modal.getCards()).clone();
                        Log.d("3334", "Opponent Card List Filled " + opponentCardList.size());
                        Log.d("3334", modal.getRemaining() + " left in deck");
                    }
                }

                @Override
                public void onFailure(Call<DeckResponse> call, Throwable t) {
                    Log.d("3334", "List Fill Failed");
                }
            });


        }
    }

    public void newGame(){
        playerCardList.clear();
        opponentCardList.clear();

        getNewDeck();

        final Handler handler = new Handler();
        Runnable playerPopulate = new Runnable() {
            @Override
            public void run() {
                fillOneCardList(playerCardList);
                if(playerCardList.size() == 0) {
                    handler.postDelayed(this, 2000);
                }
            }
        };
        handler.post(playerPopulate);

        Runnable opponentPopulate = new Runnable() {
            @Override
            public void run() {
                fillOneCardList(opponentCardList);
                if(opponentCardList.size() == 0) {
                    handler.postDelayed(this, 2000);
                }
            }
        };
        handler.post(opponentPopulate);
    }

    public Card pullTopCard(List<Card> cardList){
        if(cardList == playerCardList && playerCardList.size() > 0) {
            //playerCard = ((ArrayDeque<Card>) cardList).removeFirst();
            playerCard = cardList.get(0);
            cardList.remove(0);
            return playerCard;
        }
        else if(cardList == opponentCardList && opponentCardList.size() >0){
            //opponentCard = ((ArrayDeque<Card>) cardList).removeFirst();
            opponentCard = cardList.get(0);
            cardList.remove(0);
            return opponentCard;
        }
        else{
            return new Card("","","","");
        }
    }

    public void cardToList(List<Card> cardList, Card card){
        cardList.add(card);
    }

    public Integer compareCards(){
        if(playerCard != null && opponentCard != null) {
            if (playerCard.getRank() > opponentCard.getRank()) {
                return 1;
            } else if (playerCard.getRank() < opponentCard.getRank()) {
                return -1;
            } else if (playerCard.getRank() == opponentCard.getRank()) {
                return 0;
            } else {
                return 9;
            }
        }
        else{
            return 9;
        }
    }

    public Integer getPlayerCardListSize(){
        return playerCardList.size();
    }

    public Integer getOpponentCardListSize(){
        return opponentCardList.size();
    }

}
