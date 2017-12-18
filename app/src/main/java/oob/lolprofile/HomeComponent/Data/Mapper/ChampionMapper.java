package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonObject;

import oob.lolprofile.HomeComponent.Domain.Model.Champion;

class ChampionMapper {
    static Champion parseChampionResponse(JsonObject championResponse) {
        Champion champion = new Champion();

        champion
                .setId(championResponse.get("id").getAsInt())
                .setImage(championResponse.getAsJsonObject("image").get("full").getAsString())
                .setLore(championResponse.get("lore").getAsString())
                .setName(championResponse.get("name").getAsString())
                .setTitle(championResponse.get("title").getAsString())
                .setSkins(SkinCollectionMapper.parseSkins(championResponse.getAsJsonArray("skins")))
        ;

        return champion;
    }
}
