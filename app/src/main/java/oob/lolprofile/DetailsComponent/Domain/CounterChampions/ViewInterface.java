package oob.lolprofile.DetailsComponent.Domain.CounterChampions;

import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;

public interface ViewInterface {
    void showCounters(ArrayList<ChampionRoleCounter> championsRoleCounter);

    void showError(String text);

    void showError();
}
