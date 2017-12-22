package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.Counter;

class CounterCollectionMapper {
    static ArrayList<Counter> parseCounters(JsonArray matchUp, int currentChampId) {
        ArrayList<Counter> counters = new ArrayList<>();

        for(JsonElement jsonElement: matchUp) {
            counters.add(CounterMapper.parseCounters((JsonObject) jsonElement, currentChampId));
        }

        Collections.sort(counters);
        return counters;
    }
}
