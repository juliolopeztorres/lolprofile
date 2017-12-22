package oob.lolprofile.DetailsComponent.Data;

import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CounterChampionClientServiceInterface {
    String CONTENT_TYPE = "Content-Type: application/json";
    String ACCEPT = "Accept: application/json";

    @Headers({CONTENT_TYPE, ACCEPT})
    @GET("champions/{id}")
    Call<ArrayList<ChampionRoleCounter>> getCountersByChampionId(
            @Path("id") int id,
            @Query("api_key") String api_key,
            @Query("elo") String elo,
            @Query("limit") int limit,
            @Query("champData") String champData,
            @Query("sort") String sort
    );
}
