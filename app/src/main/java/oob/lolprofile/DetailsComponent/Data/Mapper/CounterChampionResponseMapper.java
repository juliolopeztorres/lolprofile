package oob.lolprofile.DetailsComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;

public class CounterChampionResponseMapper implements JsonDeserializer<ArrayList<ChampionRoleCounter>> {

    @Override
    public ArrayList<ChampionRoleCounter> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ChampionRoleCounterCollectionMapper.parseCounterChampionResponse((JsonArray) json);
    }
}
