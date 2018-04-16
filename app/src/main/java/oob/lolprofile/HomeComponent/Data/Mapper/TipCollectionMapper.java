package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import io.realm.RealmList;

class TipCollectionMapper {
    static ArrayList<String> parseTips(JsonArray tipsResponse) {
        ArrayList<String> tips = new ArrayList<>();

        for(JsonElement tipResponse: tipsResponse) {
            tips.add(tipResponse.getAsString());
        }

        return tips;
    }

    static ArrayList<String> parseTipsRealm(RealmList<String> tipRows) {
        return new ArrayList<>(tipRows);
    }

    static RealmList<String> parseTipsToRealm(ArrayList<String> tips) {
        RealmList<String> tipRows = new RealmList<>();
        tipRows.addAll(tips);

        return tipRows;
    }
}
