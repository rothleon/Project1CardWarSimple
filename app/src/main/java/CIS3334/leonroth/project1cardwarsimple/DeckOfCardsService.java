package CIS3334.leonroth.project1cardwarsimple;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeckOfCardsService {

    @GET("new/shuffle/?deck_count=1")
    Call<DeckResponse> newDeck();

    @GET("{deck_id}/draw/?count=26")
    Call<DeckResponse> drawHalfDeck(@Path("deck_id") String deck_id);
}
