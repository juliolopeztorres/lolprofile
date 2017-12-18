package oob.lolprofile.HomeComponent.Data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import oob.lolprofile.HomeComponent.Domain.ChampionRepositoryInterface;
import oob.lolprofile.HomeComponent.Domain.Model.Champion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionRepository implements ChampionRepositoryInterface {
    private ChampionClientInterface championClientInterface;
    private String locale;
    private String apiKey;

    public ChampionRepository(ChampionClientInterface championClientInterface, String locale, String apiKey) {
        this.championClientInterface = championClientInterface;
        this.locale = locale;
        this.apiKey = apiKey;
    }

    @Override
    public void getAll(final ChampionCallback callback) {
        Call<ArrayList<Champion>> serviceCall = this.championClientInterface.getAll(
                this.locale,
                true,
                new String[] {"image", "lore", "skins"},
                this.apiKey
        );

        serviceCall.enqueue(new Callback<ArrayList<Champion>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Champion>> call, @NonNull Response<ArrayList<Champion>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Champion>> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
