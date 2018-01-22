package oob.lolprofile.DetailsComponent.Data.Mapper;

import oob.lolprofile.HomeComponent.Data.Model.ChampionRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

class ChampionMapper {
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
        ;

        return champion;
    }
}
