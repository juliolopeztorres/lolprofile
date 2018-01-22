package oob.lolprofile.DetailsComponent.Data.Mapper;

import java.util.ArrayList;

import io.realm.RealmList;
import oob.lolprofile.HomeComponent.Data.Model.SkinRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Skin;

class SkinCollectionMapper {
    static ArrayList<Skin> parseSkinsRealm(RealmList<SkinRow> skinRows) {
        ArrayList<Skin> skins = new ArrayList<>();

        for(SkinRow skinRow : skinRows) {
            skins.add(SkinMapper.parseSkinRealm(skinRow));
        }

        return skins;
    }
}
