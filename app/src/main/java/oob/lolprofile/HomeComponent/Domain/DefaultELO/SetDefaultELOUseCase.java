package oob.lolprofile.HomeComponent.Domain.DefaultELO;

public class SetDefaultELOUseCase {
    private PreferencesInterface preferences;

    public SetDefaultELOUseCase(PreferencesInterface preferences) {
        this.preferences = preferences;
    }

    public void setDefaultELO(String elo) {
        this.preferences.setDefaultELO(elo);
    }
}
