package oob.lolprofile.DetailsComponent.Data.Mapper;

import java.util.ArrayList;

import io.realm.RealmResults;
import oob.lolprofile.HomeComponent.Data.Model.ChampionRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;

public class ChampionCollectionMapper {
    public static ArrayList<Champion> parseChampionsRealmResponse(RealmResults<ChampionRow> championRows) {
        ArrayList<Champion> champions = new ArrayList<>();

        for (ChampionRow championRow : championRows) {
            champions.add(ChampionMapper.parseChampionRealmResponse(championRow));
        }

        return champions;
    }
}
