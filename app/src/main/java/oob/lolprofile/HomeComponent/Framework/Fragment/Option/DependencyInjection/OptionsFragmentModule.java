package oob.lolprofile.HomeComponent.Framework.Fragment.Option.DependencyInjection;

import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import oob.lolprofile.HomeComponent.Data.Repository.ChampionDBRepository;
import oob.lolprofile.HomeComponent.Data.Repository.PreferencesRepository;
import oob.lolprofile.HomeComponent.Domain.DefaultELO.GetDefaultELOUseCase;
import oob.lolprofile.HomeComponent.Domain.DefaultELO.PreferencesInterface;
import oob.lolprofile.HomeComponent.Domain.DefaultELO.SetDefaultELOUseCase;
import oob.lolprofile.HomeComponent.Domain.DefaultRowNumber.GetDefaultRowNumberUseCase;
import oob.lolprofile.HomeComponent.Domain.DefaultRowNumber.SetDefaultRowNumberUseCase;
import oob.lolprofile.HomeComponent.Domain.DeleteStoredData.ChampionDBInterface;
import oob.lolprofile.HomeComponent.Domain.DeleteStoredData.DeleteStoredDataUseCase;

@Module
public class OptionsFragmentModule {

    private String keyDefaultELO;
    private String keyDefaultRowNumber;

    public OptionsFragmentModule(String keyDefaultELO, String keyDefaultRowNumber) {
        this.keyDefaultELO = keyDefaultELO;
        this.keyDefaultRowNumber = keyDefaultRowNumber;
    }

    @Provides
    @OptionsFragmentScopeInterface
    DeleteStoredDataUseCase provideDeleteStoredDataUseCase(ChampionDBInterface championDBInterface) {
        return new DeleteStoredDataUseCase(championDBInterface);
    }

    @Provides
    @OptionsFragmentScopeInterface
    ChampionDBInterface provideChampionDBInterface(Realm realm) {
        return new ChampionDBRepository(realm);
    }

    @Provides
    @OptionsFragmentScopeInterface
    SetDefaultELOUseCase provideSetDefaultELOUseCase(PreferencesInterface preferences) {
        return new SetDefaultELOUseCase(preferences);
    }

    @Provides
    @OptionsFragmentScopeInterface
    GetDefaultELOUseCase provideGetDefaultELOUseCase(PreferencesInterface preferences) {
        return new GetDefaultELOUseCase(preferences);
    }

    @Provides
    @OptionsFragmentScopeInterface
    GetDefaultRowNumberUseCase provideGetDefaultRowNumberUseCase(oob.lolprofile.HomeComponent.Domain.DefaultRowNumber.PreferencesInterface preferences) {
        return new GetDefaultRowNumberUseCase(preferences);
    }

    @Provides
    @OptionsFragmentScopeInterface
    SetDefaultRowNumberUseCase provideSetDefaultRowNumberUseCase(oob.lolprofile.HomeComponent.Domain.DefaultRowNumber.PreferencesInterface preferences) {
        return new SetDefaultRowNumberUseCase(preferences);
    }

    @Provides
    @OptionsFragmentScopeInterface
    PreferencesInterface providePreferencesInterface(SharedPreferences preferences) {
        return new PreferencesRepository(preferences, this.keyDefaultELO, this.keyDefaultRowNumber);
    }

    @Provides
    @OptionsFragmentScopeInterface
    oob.lolprofile.HomeComponent.Domain.DefaultRowNumber.PreferencesInterface providePreferencesInterfaceRowNumber(SharedPreferences preferences) {
        return new PreferencesRepository(preferences, this.keyDefaultELO, this.keyDefaultRowNumber);
    }
}
