package oob.lolprofile.HomeComponent.Data.Repository;

import android.content.SharedPreferences;

import oob.lolprofile.HomeComponent.Domain.DefaultELO.PreferencesInterface;

public class PreferencesRepository implements PreferencesInterface, oob.lolprofile.HomeComponent.Domain.DefaultRowNumber.PreferencesInterface{

    private SharedPreferences preferences;
    private String keyDefaultELO;
    private String keyDefaultRowNumber;

    public PreferencesRepository(SharedPreferences preferences, String keyDefaultELO, String keyDefaultRowNumber) {
        this.preferences = preferences;
        this.keyDefaultELO = keyDefaultELO;
        this.keyDefaultRowNumber = keyDefaultRowNumber;
    }

    @Override
    public String getDefaultELO() {
        return this.preferences.getString(this.keyDefaultELO, "NOPE");
    }

    @Override
    public void setDefaultELO(String elo) {
        this.preferences.edit().putString(this.keyDefaultELO, elo).apply();
    }

    @Override
    public String getDefaultRowNumber() {
        return this.preferences.getString(this.keyDefaultRowNumber, "NOPE");
    }

    @Override
    public void setDefaultRowNumber(String rowNumber) {
        this.preferences.edit().putString(this.keyDefaultRowNumber, rowNumber).apply();
    }
}
