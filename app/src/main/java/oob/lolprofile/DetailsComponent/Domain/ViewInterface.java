package oob.lolprofile.DetailsComponent.Domain;

import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.Model.ChampionRoleCounter;

public interface ViewInterface {
    void showCounters(ArrayList<ChampionRoleCounter> championsRoleCounter);

    void showError(String text);
}
