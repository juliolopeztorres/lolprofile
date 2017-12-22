package oob.lolprofile.HomeComponent.Data.Repository;

import android.content.SharedPreferences;

import oob.lolprofile.HomeComponent.Domain.DefaultELO.PreferencesInterface;

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

    @Override
    public void setDefaultELO(String elo) {
        this.preferences.edit().putString(this.keyDefaultELO, elo).apply();
    }
}
