package oob.lolprofile.DetailsComponent.Framework.DependencyInjection;

import android.content.SharedPreferences;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import oob.lolprofile.DetailsComponent.Data.CounterChampionClientServiceInterface;
import oob.lolprofile.DetailsComponent.Data.Repository.ChampionDBRepository;
import oob.lolprofile.DetailsComponent.Data.Repository.ChampionRepository;
import oob.lolprofile.DetailsComponent.Data.Repository.CounterChampionRepository;
import oob.lolprofile.DetailsComponent.Data.Repository.PreferencesRepository;
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.CounterChampionRepositoryInterface;
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.GetCounterChampionsByChampionIdUseCase;
import oob.lolprofile.DetailsComponent.Domain.DefaultELO.GetDefaultELOUseCase;
import oob.lolprofile.DetailsComponent.Domain.DefaultELO.PreferencesInterface;
import oob.lolprofile.DetailsComponent.Domain.DefaultRowNumber.GetDefaultRowNumberUseCase;
import oob.lolprofile.DetailsComponent.Domain.GetAllChampions.ChampionRepositoryInterface;
import oob.lolprofile.DetailsComponent.Domain.GetAllChampions.GetAllChampionsUseCase;
import retrofit2.Retrofit;

@Module
public class DetailsActivityModule {
    private DetailsActivityViewInterface view;
    private String apiKey;
    private String keyDefaultELO;
    private String keyDefaultRowNumber;

    public DetailsActivityModule(DetailsActivityViewInterface view, String apiKey, String keyDefaultELO, String keyDefaultRowNumber) {
        this.view = view;
        this.apiKey = apiKey;
        this.keyDefaultELO = keyDefaultELO;
        this.keyDefaultRowNumber = keyDefaultRowNumber;
    }

    @DetailsActivityScopeInterface
    @Provides
    GetAllChampionsUseCase provideGetAllChampionsUseCase(ChampionRepositoryInterface championRepositoryInterface) {
        return new GetAllChampionsUseCase(this.view, championRepositoryInterface);
    }

    @DetailsActivityScopeInterface
    @Provides
    ChampionRepositoryInterface provideChampionRepositoryInterface(ChampionDBRepository championDBRepository) {
        return new ChampionRepository(championDBRepository);
    }

    @DetailsActivityScopeInterface
    @Provides
    ChampionDBRepository provideChampionDBRepository(Realm realm) {
        return new ChampionDBRepository(realm);
    }

    @DetailsActivityScopeInterface
    @Provides
    GetCounterChampionsByChampionIdUseCase provideGetCounterChampionsByChampionIdUseCase(CounterChampionRepositoryInterface counterChampionRepositoryInterface) {
        return new GetCounterChampionsByChampionIdUseCase(this.view, counterChampionRepositoryInterface);
    }

    @DetailsActivityScopeInterface
    @Provides
    CounterChampionRepositoryInterface provideCounterChampionRepositoryInterface(CounterChampionClientServiceInterface counterChampionClientServiceInterface) {
        return new CounterChampionRepository(counterChampionClientServiceInterface, this.apiKey);
    }

    @DetailsActivityScopeInterface
    @Provides
    CounterChampionClientServiceInterface provideCounterChampionClientServiceInterface(@Named("clientChampion") Retrofit retrofit) {
        return retrofit.create(CounterChampionClientServiceInterface.class);
    }

    @DetailsActivityScopeInterface
    @Provides
    GetDefaultELOUseCase provideGetDefaultELOUseCase(PreferencesInterface preferences) {
        return new GetDefaultELOUseCase(preferences);
    }

    @DetailsActivityScopeInterface
    @Provides
    GetDefaultRowNumberUseCase provideGetDefaultRowNumberUseCase(oob.lolprofile.DetailsComponent.Domain.DefaultRowNumber.PreferencesInterface preferences) {
        return new GetDefaultRowNumberUseCase(preferences);
    }

    @DetailsActivityScopeInterface
    @Provides
    PreferencesInterface providePreferencesInterface(SharedPreferences preferences) {
        return new PreferencesRepository(preferences, this.keyDefaultELO, this.keyDefaultRowNumber);
    }

    @DetailsActivityScopeInterface
    @Provides
    oob.lolprofile.DetailsComponent.Domain.DefaultRowNumber.PreferencesInterface providePreferencesInterfaceRowNumber(SharedPreferences preferences) {
        return new PreferencesRepository(preferences, this.keyDefaultELO, this.keyDefaultRowNumber);
    }
}
