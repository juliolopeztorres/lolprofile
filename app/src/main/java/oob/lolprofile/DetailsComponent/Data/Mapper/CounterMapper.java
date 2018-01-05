package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonObject;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.Counter;

class CounterMapper {
    static Counter parseCounters(JsonObject matchUp, int currentChampId) {
        Counter counter = new Counter();
        String keyCounterChampId = "champ2_id";
        String keyCounterChamp = "champ2";

        if (matchUp.get(keyCounterChampId).getAsInt() == currentChampId) {
            keyCounterChampId = "champ1_id";
            keyCounterChamp = "champ1";
        }

        JsonObject counterChampInfo = matchUp.get(keyCounterChamp).getAsJsonObject();

        counter
                .setId(matchUp.get(keyCounterChampId).getAsInt())
                .setWinRate(counterChampInfo.get("winrate").getAsDouble())
                .setWins(counterChampInfo.get("wins").getAsInt())
        ;

        return counter;
    }
}

