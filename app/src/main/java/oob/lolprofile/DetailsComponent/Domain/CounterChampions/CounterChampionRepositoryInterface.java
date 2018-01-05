package oob.lolprofile.DetailsComponent.Domain.CounterChampions;

import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;

public interface CounterChampionRepositoryInterface {
    void getCountersByChampionId(CounterChampionCallback callback, int championId, String elo);

    interface CounterChampionCallback {
        void onSuccess(ArrayList<ChampionRoleCounter> champions);

        void onError(String text);
    }
}
