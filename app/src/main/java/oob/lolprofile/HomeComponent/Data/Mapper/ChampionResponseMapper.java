package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import oob.lolprofile.HomeComponent.Domain.Model.Champion;

public class ChampionResponseMapper implements JsonDeserializer<ArrayList<Champion>> {

    @Override
    public ArrayList<Champion> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ChampionCollectionMapper.parseChampionsResponse(((JsonObject) json).getAsJsonObject("data"));
    }
}
