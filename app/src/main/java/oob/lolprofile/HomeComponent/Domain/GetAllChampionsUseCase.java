package oob.lolprofile.HomeComponent.Domain;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.Model.Champion;

public class GetAllChampionsUseCase implements ChampionRepositoryInterface.ChampionCallback {
    private ViewInterface viewInterface;
    private ChampionRepositoryInterface championRepositoryInterface;

    public GetAllChampionsUseCase(ViewInterface viewInterface, ChampionRepositoryInterface championRepositoryInterface) {
        this.viewInterface = viewInterface;
        this.championRepositoryInterface = championRepositoryInterface;
    }

    public void getAll() {
        this.championRepositoryInterface.getAll(this);
    }

    @Override
    public void onSuccess(ArrayList<Champion> champions) {
        this.viewInterface.showChampions(champions);
    }

    @Override
    public void onError(String text) {
        this.viewInterface.showError(text);
    }
}
