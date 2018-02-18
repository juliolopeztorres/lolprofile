package oob.lolprofile.DetailsComponent.Domain.DefaultRowNumber;

public class GetDefaultRowNumberUseCase {
    private PreferencesInterface preferences;

    public GetDefaultRowNumberUseCase(PreferencesInterface preferences) {
        this.preferences = preferences;
    }

    public int getDefaultRowNumber() {
        return this.preferences.getDefaultRowNumber();
    }
}
