package oob.lolprofile.HomeComponent.Domain;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.Model.Champion;

public interface ChampionRepositoryInterface {
    void getAll(ChampionCallback callback);

    interface ChampionCallback {
        void onSuccess(ArrayList<Champion> champions);

        void onError(String text);
    }
}
