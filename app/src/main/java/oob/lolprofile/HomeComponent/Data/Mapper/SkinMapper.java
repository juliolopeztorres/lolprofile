package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonObject;

import oob.lolprofile.HomeComponent.Domain.Model.Skin;

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
}
