package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonElement;

import java.util.Map;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.RoleCounter;

class RoleCounterMapper {
    static RoleCounter parseRoleCounter(Map.Entry<String, JsonElement> matchUp, int currentChampId) {
        RoleCounter roleCounter = new RoleCounter();

        roleCounter
                .setRole(matchUp.getKey())
                .setCounters(CounterCollectionMapper.parseCounters(matchUp.getValue().getAsJsonArray(), currentChampId))
        ;

        return roleCounter;
    }
}
