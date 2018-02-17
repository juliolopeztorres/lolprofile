package oob.lolprofile.HomeComponent.Domain.DefaultRowNumber;

public class SetDefaultRowNumberUseCase {
    private PreferencesInterface preferences;

    public SetDefaultRowNumberUseCase(PreferencesInterface preferences) {
        this.preferences = preferences;
    }

    public void setDefaultRowNumber(String rowNumber) {
        this.preferences.setDefaultRowNumber(rowNumber);
    }
}
