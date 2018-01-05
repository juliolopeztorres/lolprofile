package oob.lolprofile.HomeComponent.Data;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ChampionClientServiceInterface {
    String CONTENT_TYPE = "Content-Type: application/json";
    String ACCEPT = "Accept: application/json";

    @Headers({CONTENT_TYPE, ACCEPT})
    @GET("champions")
    Call<ArrayList<Champion>> getAll(
            @Query("locale") String locale,
            @Query("dataById") boolean dataById,
            @Query("tags") String[] tags,
            @Query("api_key") String api_key
    );
}
