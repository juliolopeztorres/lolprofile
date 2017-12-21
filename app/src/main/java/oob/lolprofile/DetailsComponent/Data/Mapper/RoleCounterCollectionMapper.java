package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

import oob.lolprofile.DetailsComponent.Domain.Model.RoleCounter;

class RoleCounterCollectionMapper {
    static ArrayList<RoleCounter> parseRoleCounters(JsonObject matchUps) {
        ArrayList<RoleCounter> roleCounters = new ArrayList<>();
        for (Map.Entry<String, JsonElement> matchUp : matchUps.entrySet()) {
            roleCounters.add(RoleCounterMapper.parseRoleCounter(matchUp));
        }

        return roleCounters;
    }
}
