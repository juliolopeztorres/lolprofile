package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

import io.realm.RealmResults;
import oob.lolprofile.HomeComponent.Data.Model.ChampionRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

public class ChampionCollectionMapper {
    static ArrayList<Champion> parseChampionsResponse(JsonObject ritoChampionResponse) {
        ArrayList<Champion> champions = new ArrayList<>();

        for (Map.Entry<String, JsonElement> championsResponse : ritoChampionResponse.entrySet()) {
            champions.add(ChampionMapper.parseChampionResponse(championsResponse.getValue().getAsJsonObject()));
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
