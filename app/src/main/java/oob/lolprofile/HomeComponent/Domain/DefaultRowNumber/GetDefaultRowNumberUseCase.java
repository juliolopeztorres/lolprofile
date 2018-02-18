package oob.lolprofile.HomeComponent.Domain.DefaultRowNumber;

public class GetDefaultRowNumberUseCase {
    private PreferencesInterface preferences;

    public GetDefaultRowNumberUseCase(PreferencesInterface preferences) {
        this.preferences = preferences;
    }

    public String getDefaultRowNumber() {
        return this.preferences.getDefaultRowNumber();
    }
}
