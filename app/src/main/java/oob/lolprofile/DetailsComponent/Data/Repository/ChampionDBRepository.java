package oob.lolprofile.DetailsComponent.Data.Repository;

import io.realm.Realm;
import io.realm.RealmResults;
import oob.lolprofile.DetailsComponent.Data.NoChampionsFoundDBException;
import oob.lolprofile.HomeComponent.Data.Model.ChampionRow;

public class ChampionDBRepository {
    private Realm realm;

    public ChampionDBRepository(Realm realm) {
        this.realm = realm;
    }

    RealmResults<ChampionRow> getAll() throws NoChampionsFoundDBException {
        RealmResults<ChampionRow> championRows = this.realm.where(ChampionRow.class).findAll();
        if (championRows.size() == 0) {
            throw new NoChampionsFoundDBException();
        }

        return championRows;
    }
}
