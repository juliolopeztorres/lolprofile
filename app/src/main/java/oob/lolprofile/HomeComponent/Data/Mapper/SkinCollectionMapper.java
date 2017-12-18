package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import oob.lolprofile.HomeComponent.Domain.Model.Skin;

class SkinCollectionMapper {
    static ArrayList<Skin> parseSkins(JsonArray skinsResponse) {
        ArrayList<Skin> skins = new ArrayList<>();

        for(JsonElement skinResponse: skinsResponse) {
            skins.add(SkinMapper.parseSkin(skinResponse.getAsJsonObject()));
        }

        return skins;
    }
}
