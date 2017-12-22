package oob.lolprofile.DetailsComponent.Data.Repository;

import android.support.annotation.NonNull;
import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Data.CounterChampionClientServiceInterface;
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.CounterChampionRepositoryInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CounterChampionRepository implements CounterChampionRepositoryInterface {
    private CounterChampionClientServiceInterface counterChampionClientServiceInterface;
    private String apiKey;

    public CounterChampionRepository(CounterChampionClientServiceInterface counterChampionClientServiceInterface, String apiKey) {
        this.counterChampionClientServiceInterface = counterChampionClientServiceInterface;
        this.apiKey = apiKey;
    }

    @Override
    public void getCountersByChampionId(final CounterChampionCallback callback, int championId, String elo) {
        Call<ArrayList<ChampionRoleCounter>> serviceCall = this.counterChampionClientServiceInterface.getCountersByChampionId(
                championId,
                this.apiKey,
                elo,
                200,
                "kda,matchups",
                "winRate-desc"
        );

        serviceCall.enqueue(new Callback<ArrayList<ChampionRoleCounter>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ChampionRoleCounter>> call, @NonNull Response<ArrayList<ChampionRoleCounter>> response) {
                ArrayList<ChampionRoleCounter> champions = response.body();
                callback.onSuccess(champions);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ChampionRoleCounter>> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
