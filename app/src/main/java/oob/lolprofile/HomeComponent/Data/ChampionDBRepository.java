package oob.lolprofile.HomeComponent.Data;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
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

    void setAll(final ArrayList<ChampionRow> championRows) {
        this.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealm(championRows);
            }
        });
    }

    void removeAll() {
        this.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.deleteAll();
            }
        });

    }
}
