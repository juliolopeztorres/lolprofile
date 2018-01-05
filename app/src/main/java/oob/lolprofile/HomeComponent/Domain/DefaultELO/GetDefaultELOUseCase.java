package oob.lolprofile.HomeComponent.Domain.DefaultELO;

public class GetDefaultELOUseCase {
    private PreferencesInterface preferences;

    public GetDefaultELOUseCase(PreferencesInterface preferences) {
        this.preferences = preferences;
    }

    public String getDefaultELO() {
        return this.preferences.getDefaultELO();
    }
}
