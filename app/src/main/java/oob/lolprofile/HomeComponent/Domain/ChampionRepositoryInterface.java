package oob.lolprofile.HomeComponent.Domain;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.Model.Champion;

public interface ChampionRepositoryInterface {
    void getAll(ChampionCallback callback);

    void removeAll();

    int getSecondsLastRequest();

    void setSecondsLastRequest(int secondsCurrent);

    interface ChampionCallback {
        void onSuccess(ArrayList<Champion> champions);

        void onError(String text);
    }
}
