package oob.lolprofile.HomeComponent.Domain.GetAllChampions;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

public interface ViewInterface {
    void showChampions(ArrayList<Champion> champions);

    void showError(String text);

    void showError();
}
