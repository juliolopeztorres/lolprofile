package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;

class ChampionRoleCounterCollectionMapper {
    static ArrayList<ChampionRoleCounter> parseCounterChampionResponse(JsonArray championResponse) {
        ArrayList<ChampionRoleCounter> championRoleCounters = new ArrayList<>();
        for (JsonElement jsonElement : championResponse) {
            championRoleCounters.add(ChampionRoleCounterMapper.parseChampionRoleCounter(jsonElement.getAsJsonObject()));
        }

        return championRoleCounters;
    }
}
