package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonObject;

import oob.lolprofile.HomeComponent.Data.Model.SkinRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Skin;

class SkinMapper {
    static Skin parseSkin(JsonObject skinResponse) {
        Skin skin = new Skin();

        skin
                .setId(skinResponse.get("id").getAsInt())
                .setName(skinResponse.get("name").getAsString())
                .setNum(skinResponse.get("num").getAsInt())
        ;

        return skin;
    }

    static Skin parseSkinRealm(SkinRow skinRow) {
        Skin skin = new Skin();

        skin
                .setId(skinRow.getId())
                .setName(skinRow.getName())
                .setNum(skinRow.getNum())
        ;

        return skin;
    }

    static SkinRow parseSkinToRealm(Skin skin) {
        SkinRow skinRow = new SkinRow();

        skinRow
                .setId(skin.getId())
                .setName(skin.getName())
                .setNum(skin.getNum())
        ;

        return skinRow;
    }
}
