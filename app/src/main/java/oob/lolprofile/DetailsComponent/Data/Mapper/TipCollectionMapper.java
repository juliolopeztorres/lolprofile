package oob.lolprofile.DetailsComponent.Data.Mapper;

import java.util.ArrayList;

import io.realm.RealmList;

class TipCollectionMapper {
    static ArrayList<String> parseTipsRealm(RealmList<String> tipRows) {
        return new ArrayList<>(tipRows);
    }
}
