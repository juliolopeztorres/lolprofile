package oob.lolprofile.HomeComponent.Domain;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.Model.Champion;

public interface ViewInterface {
    void showChampions(ArrayList<Champion> champions);

    void showError(String text);
}
