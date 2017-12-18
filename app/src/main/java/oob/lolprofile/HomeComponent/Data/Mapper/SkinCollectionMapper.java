package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import io.realm.RealmList;
import oob.lolprofile.HomeComponent.Data.Model.SkinRow;
import oob.lolprofile.HomeComponent.Domain.Model.Skin;

class SkinCollectionMapper {
    static ArrayList<Skin> parseSkins(JsonArray skinsResponse) {
        ArrayList<Skin> skins = new ArrayList<>();

        for(JsonElement skinResponse: skinsResponse) {
            skins.add(SkinMapper.parseSkin(skinResponse.getAsJsonObject()));
        }

        return skins;
    }

    static ArrayList<Skin> parseSkinsRealm(RealmList<SkinRow> skinRows) {
        ArrayList<Skin> skins = new ArrayList<>();

        for(SkinRow skinRow : skinRows) {
            skins.add(SkinMapper.parseSkinRealm(skinRow));
        }

        return skins;
    }

    static RealmList<SkinRow> parseSkinsToRealm(ArrayList<Skin> skins) {
        RealmList<SkinRow> skinRows = new RealmList<>();

        for(Skin skin: skins) {
            skinRows.add(SkinMapper.parseSkinToRealm(skin));
        }

        return skinRows;
    }
}
