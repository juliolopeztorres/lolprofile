package oob.lolprofile.DetailsComponent.Framework.DependencyInjection;

import android.content.SharedPreferences;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import oob.lolprofile.DetailsComponent.Data.CounterChampionClientServiceInterface;
import oob.lolprofile.DetailsComponent.Data.CounterChampionRepository;
import oob.lolprofile.DetailsComponent.Domain.CounterChampionRepositoryInterface;
import oob.lolprofile.DetailsComponent.Domain.GetCounterChampionsByChampionIdUseCase;
import oob.lolprofile.DetailsComponent.Domain.ViewInterface;
import oob.lolprofile.HomeComponent.Data.Repository.ChampionDBRepository;
import oob.lolprofile.HomeComponent.Data.Repository.ChampionMockRepository;
import oob.lolprofile.HomeComponent.Domain.ChampionRepositoryInterface;
import retrofit2.Retrofit;

@Module
public class DetailsActivityModule {
    private ViewInterface view;
    private String apiKey;

    public DetailsActivityModule(ViewInterface view, String apiKey) {
        this.view = view;
        this.apiKey = apiKey;
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
    ChampionRepositoryInterface provideChampionMockRepositoryInterface(ChampionDBRepository championDBRepository, SharedPreferences sharedPreferences) {
        return new ChampionMockRepository(championDBRepository, sharedPreferences, "randomKey");
    }

    @DetailsActivityScopeInterface
    @Provides
    ChampionDBRepository provideChampionDBRepository(Realm realm) {
        return new ChampionDBRepository(realm);
    }
}
