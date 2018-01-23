package oob.lolprofile.DetailsComponent.Domain.CounterChampions;

import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;

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

    @Override
    public void onError() {
        this.viewInterface.showError();
    }
}
