package oob.lolprofile.HomeComponent.Data.Mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import javax.annotation.Nullable;

import oob.lolprofile.HomeComponent.Data.Model.ChampionRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

class ChampionMapper {
    static Champion parseChampionResponse(JsonObject championResponse) {
        Champion champion = new Champion();

        champion
                .setId(championResponse.get("id").getAsInt())
                .setKey(championResponse.get("key").getAsString())
                .setImage(championResponse.getAsJsonObject("image").get("full").getAsString())
                .setLore(championResponse.get("lore").getAsString())
                .setName(championResponse.get("name").getAsString())
                .setTitle(championResponse.get("title").getAsString())
                .setSkins(SkinCollectionMapper.parseSkins(championResponse.getAsJsonArray("skins")))
        ;

        champion.setAllyTips(getTips(championResponse.getAsJsonArray("allytips")));
        champion.setEnemyTips(getTips(championResponse.getAsJsonArray("enemytips")));

        return champion;
    }

    private static ArrayList<String> getTips(@Nullable JsonArray tips) {
        if (tips == null) {
            return new ArrayList<>();
        }

        return TipCollectionMapper.parseTips(tips);
    }

    static Champion parseChampionRealmResponse(ChampionRow championRow) {
        Champion champion = new Champion();

        champion
                .setId(championRow.getId())
                .setKey(championRow.getKey())
                .setImage(championRow.getImage())
                .setLore(championRow.getLore())
                .setName(championRow.getName())
                .setTitle(championRow.getTitle())
                .setSkins(SkinCollectionMapper.parseSkinsRealm(championRow.getSkins()))
                .setAllyTips(TipCollectionMapper.parseTipsRealm(championRow.getAllyTips()))
                .setEnemyTips(TipCollectionMapper.parseTipsRealm(championRow.getEnemyTips()))
        ;

        return champion;
    }

    static ChampionRow parseChampionToRealm(Champion champion) {
        ChampionRow championRow = new ChampionRow();

        championRow
                .setId(champion.getId())
                .setKey(champion.getKey())
                .setImage(champion.getImage())
                .setLore(champion.getLore())
                .setName(champion.getName())
                .setTitle(champion.getTitle())
                .setSkins(SkinCollectionMapper.parseSkinsToRealm(champion.getSkins()))
                .setAllyTips(TipCollectionMapper.parseTipsToRealm(champion.getAllyTips()))
                .setEnemyTips(TipCollectionMapper.parseTipsToRealm(champion.getEnemyTips()))
        ;

        return championRow;
    }
}
