package oob.lolprofile.HomeComponent.Data;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.Model.Champion;
import oob.lolprofile.HomeComponent.Domain.ChampionRepositoryInterface;

public class ChampionMockRepository implements ChampionRepositoryInterface {

    @Override
    public void getAll(ChampionCallback callback) {
        callback.onSuccess(getChampions());
    }

    private Champion getChampion() {
        return new Champion().setImage("Yasuo.png");
    }

    private ArrayList<Champion> getChampions() {
        return new ArrayList<Champion>() {{
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
        }};
    }
}
