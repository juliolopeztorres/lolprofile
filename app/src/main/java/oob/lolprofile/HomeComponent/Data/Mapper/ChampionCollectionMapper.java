package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import io.realm.RealmResults;
import oob.lolprofile.HomeComponent.Data.Model.ChampionRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

public class ChampionCollectionMapper {
    static ArrayList<Champion> parseChampionsResponse(JsonArray ritoChampionResponse) {
        ArrayList<Champion> champions = new ArrayList<>();

        for (JsonElement jsonElement : ritoChampionResponse) {
            champions.add(ChampionMapper.parseChampionResponse(jsonElement.getAsJsonObject()));
        }

        return champions;
    }

    public static ArrayList<Champion> parseChampionsRealmResponse(RealmResults<ChampionRow> championRows) {
        ArrayList<Champion> champions = new ArrayList<>();

        for (ChampionRow championRow : championRows) {
            champions.add(ChampionMapper.parseChampionRealmResponse(championRow));
        }

        return champions;
    }

    public static ArrayList<ChampionRow> parseChampionsToRealm(ArrayList<Champion> champions) {
        ArrayList<ChampionRow> championRows = new ArrayList<>();

        for (Champion champion: champions) {
            championRows.add(ChampionMapper.parseChampionToRealm(champion));
        }

        return championRows;
    }
}
