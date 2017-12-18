package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

import oob.lolprofile.HomeComponent.Domain.Model.Champion;

class ChampionCollectionMapper {
    static ArrayList<Champion> parseChampionsResponse(JsonObject ritoChampionResponse) {
        ArrayList<Champion> champions = new ArrayList<>();

        for (Map.Entry<String, JsonElement> championsResponse : ritoChampionResponse.entrySet()) {
            champions.add(ChampionMapper.parseChampionResponse(championsResponse.getValue().getAsJsonObject()));
        }

        return champions;
    }
}
