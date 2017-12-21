package oob.lolprofile.DetailsComponent.Domain;

import java.util.ArrayList;
import java.util.Collections;

import oob.lolprofile.DetailsComponent.Domain.Model.ChampionRoleCounter;

public class GetCounterChampionsByChampionIdUseCase implements CounterChampionRepositoryInterface.CounterChampionCallback {
    private ViewInterface viewInterface;
    private CounterChampionRepositoryInterface championRepositoryInterface;

    public GetCounterChampionsByChampionIdUseCase(ViewInterface viewInterface, CounterChampionRepositoryInterface championRepositoryInterface) {
        this.viewInterface = viewInterface;
        this.championRepositoryInterface = championRepositoryInterface;
    }

    public void getCountersByChampionId(int championId, String elo) {
        this.championRepositoryInterface.getCountersByChampionId(this, championId, elo);
    }

    @Override
    public void onSuccess(ArrayList<ChampionRoleCounter> championRoleCounters) {
        this.viewInterface.showCounters(championRoleCounters);
    }

    @Override
    public void onError(String text) {
        this.viewInterface.showError(text);
    }
}
