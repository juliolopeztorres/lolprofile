package oob.lolprofile.HomeComponent.Data.Repository;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Data.ChampionClientServiceInterface;
import oob.lolprofile.HomeComponent.Data.Mapper.ChampionCollectionMapper;
import oob.lolprofile.HomeComponent.Data.NoChampionsFoundDBException;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.ChampionRepositoryInterface;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionRepository implements ChampionRepositoryInterface {
    private ChampionClientServiceInterface championClientInterface;
    private String locale;
    private String apiKey;
    private ChampionDBRepository championDBRepository;
    private SharedPreferences sharedPreferences;
    private String keySecondsLastRequest;

    public ChampionRepository(ChampionClientServiceInterface championClientInterface, String locale, String apiKey, ChampionDBRepository championDBRepository, SharedPreferences sharedPreferences, String keySecondsLastRequest) {
        this.championClientInterface = championClientInterface;
        this.locale = locale;
        this.apiKey = apiKey;
        this.championDBRepository = championDBRepository;
        this.sharedPreferences = sharedPreferences;
        this.keySecondsLastRequest = keySecondsLastRequest;
    }

    @Override
    public void getAll(final ChampionCallback callback) {
        try {
            callback.onSuccess(
                    ChampionCollectionMapper.parseChampionsRealmResponse(
                            this.championDBRepository.getAll()
                    )
            );
        } catch (NoChampionsFoundDBException e) {
            this.requestChampions(callback);
        }
    }

    private void requestChampions(final ChampionCallback callback) {
        Call<ArrayList<Champion>> serviceCall = this.championClientInterface.getAll(
                this.locale,
                true,
                new String[]{"image", "lore", "skins", "enemytips", "allytips"},
                this.apiKey
        );

        serviceCall.enqueue(new Callback<ArrayList<Champion>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Champion>> call, @NonNull Response<ArrayList<Champion>> response) {
                ArrayList<Champion> champions = response.body();
                if (response.code() != 200 || null == champions) {
                    callback.onError();
                    return;
                }
                championDBRepository.setAll(
                        ChampionCollectionMapper.parseChampionsToRealm(champions)
                );
                getAll(callback);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Champion>> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void removeAll() {
        this.championDBRepository.removeAll();
    }

    @Override
    public int getSecondsLastRequest() {
        return this.sharedPreferences.getInt(this.keySecondsLastRequest, 0);
    }

    @Override
    public void setSecondsLastRequest(int secondsCurrent) {
        this.sharedPreferences.edit().putInt(this.keySecondsLastRequest, secondsCurrent).apply();
    }
}
