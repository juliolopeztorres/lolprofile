package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonObject;

import oob.lolprofile.DetailsComponent.Domain.Model.ChampionRoleCounter;

class ChampionRoleCounterMapper {
    static ChampionRoleCounter parseChampionRoleCounter(JsonObject championRoleCounterResponse) {
        ChampionRoleCounter championRoleCounter = new ChampionRoleCounter();

        championRoleCounter
                .setId(championRoleCounterResponse.get("championId").getAsInt())
                .setRole(championRoleCounterResponse.get("role").getAsString())
                .setElo(championRoleCounterResponse.get("elo").getAsString())
                .setWinRate(championRoleCounterResponse.get("winRate").getAsDouble())
                .setKills(championRoleCounterResponse.get("kills").getAsDouble())
                .setDeaths(championRoleCounterResponse.get("deaths").getAsDouble())
                .setAssists(championRoleCounterResponse.get("assists").getAsDouble())
                .setPlayRate(championRoleCounterResponse.get("playRate").getAsDouble())
                .setGamesPlayed(championRoleCounterResponse.get("gamesPlayed").getAsInt())
                .setPercentRolePlayed(championRoleCounterResponse.get("percentRolePlayed").getAsDouble())
                .setBanRate(championRoleCounterResponse.get("banRate").getAsDouble())
                .setRoleCounters(RoleCounterCollectionMapper.parseRoleCounters((championRoleCounterResponse.get("matchups").getAsJsonObject())))
        ;
        
        return championRoleCounter;
    }
}
