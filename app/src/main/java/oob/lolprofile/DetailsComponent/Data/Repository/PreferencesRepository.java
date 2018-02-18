package oob.lolprofile.DetailsComponent.Data.Repository;

import android.content.SharedPreferences;

import oob.lolprofile.DetailsComponent.Domain.DefaultELO.PreferencesInterface;

public class PreferencesRepository implements PreferencesInterface, oob.lolprofile.DetailsComponent.Domain.DefaultRowNumber.PreferencesInterface {

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
    public int getDefaultRowNumber() {
        return Integer.parseInt(this.preferences.getString(this.keyDefaultRowNumber, "NOPE"));
    }
}
