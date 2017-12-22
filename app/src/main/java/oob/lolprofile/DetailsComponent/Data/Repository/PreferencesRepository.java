package oob.lolprofile.DetailsComponent.Data.Repository;

import android.content.SharedPreferences;

import oob.lolprofile.DetailsComponent.Domain.DefaultELO.PreferencesInterface;

public class PreferencesRepository implements PreferencesInterface {

    private SharedPreferences preferences;
    private String keyDefaultELO;

    public PreferencesRepository(SharedPreferences preferences, String keyDefaultELO) {
        this.preferences = preferences;
        this.keyDefaultELO = keyDefaultELO;
    }

    @Override
    public String getDefaultELO() {
        return this.preferences.getString(this.keyDefaultELO, "NOPE");
    }
}
