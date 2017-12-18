package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonObject;

import oob.lolprofile.HomeComponent.Data.Model.ChampionRow;
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

    static Champion parseChampionRealmResponse(ChampionRow championRow) {
        Champion champion = new Champion();

        champion
                .setId(championRow.getId())
                .setImage(championRow.getImage())
                .setLore(championRow.getLore())
                .setName(championRow.getName())
                .setTitle(championRow.getTitle())
                .setSkins(SkinCollectionMapper.parseSkinsRealm(championRow.getSkins()))
        ;

        return champion;
    }

    static ChampionRow parseChampionToRealm(Champion champion) {
        ChampionRow championRow = new ChampionRow();

        championRow
                .setId(champion.getId())
                .setImage(champion.getImage())
                .setLore(champion.getLore())
                .setName(champion.getName())
                .setTitle(champion.getTitle())
                .setSkins(SkinCollectionMapper.parseSkinsToRealm(champion.getSkins()))
        ;

        return championRow;
    }
}
