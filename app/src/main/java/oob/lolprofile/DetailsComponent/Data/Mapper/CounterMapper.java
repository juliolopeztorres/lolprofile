package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonObject;

import oob.lolprofile.DetailsComponent.Domain.Model.Counter;

class CounterMapper {
    static Counter parseCounters(JsonObject matchUp) {
        Counter counter = new Counter();

        JsonObject counterChampInfo = matchUp.get("champ2").getAsJsonObject();

        counter
                .setId(matchUp.get("champ2_id").getAsInt())
                .setWinRate(counterChampInfo.get("winrate").getAsDouble())
                .setWins(counterChampInfo.get("wins").getAsInt())
        ;

        return counter;
    }
}

