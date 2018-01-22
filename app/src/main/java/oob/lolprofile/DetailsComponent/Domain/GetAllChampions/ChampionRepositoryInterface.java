package oob.lolprofile.DetailsComponent.Domain.GetAllChampions;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

public interface ChampionRepositoryInterface {
    void getAll(ChampionCallback callback);

    interface ChampionCallback {
        void onSuccess(ArrayList<Champion> champions);

        void onError(String text);

        void onError();
    }
}
