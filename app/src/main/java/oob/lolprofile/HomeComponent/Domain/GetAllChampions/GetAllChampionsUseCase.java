package oob.lolprofile.HomeComponent.Domain.GetAllChampions;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

public class GetAllChampionsUseCase implements ChampionRepositoryInterface.ChampionCallback {
    private ViewInterface viewInterface;
    private ChampionRepositoryInterface championRepositoryInterface;

    public GetAllChampionsUseCase(ViewInterface viewInterface, ChampionRepositoryInterface championRepositoryInterface) {
        this.viewInterface = viewInterface;
        this.championRepositoryInterface = championRepositoryInterface;
    }

    public void getAll() {
        checkRemoveChampionsSaved();
        this.championRepositoryInterface.getAll(this);
    }

    private void checkRemoveChampionsSaved() {
        int secondsCurrent = (int) (System.currentTimeMillis() / 1000L);
        if (secondsCurrent - this.championRepositoryInterface.getSecondsLastRequest() > (2 * 86400)) {
            this.championRepositoryInterface.removeAll();
            this.championRepositoryInterface.setSecondsLastRequest(secondsCurrent);
        }
    }

    @Override
    public void onSuccess(ArrayList<Champion> champions) {
        this.viewInterface.showChampions(champions);
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
