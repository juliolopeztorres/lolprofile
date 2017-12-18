package oob.lolprofile.HomeComponent.Framework.DependencyInjection;

import android.content.SharedPreferences;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import oob.lolprofile.HomeComponent.Data.ChampionClientServiceInterface;
import oob.lolprofile.HomeComponent.Data.Repository.ChampionDBRepository;
import oob.lolprofile.HomeComponent.Data.Repository.ChampionMockRepository;
import oob.lolprofile.HomeComponent.Data.Repository.ChampionRepository;
import oob.lolprofile.HomeComponent.Domain.ChampionRepositoryInterface;
import oob.lolprofile.HomeComponent.Domain.GetAllChampionsUseCase;
import oob.lolprofile.HomeComponent.Domain.ViewInterface;
import retrofit2.Retrofit;

@Module
public class HomeActivityModule {
    private ViewInterface view;
    private String locale;
    private String apiKey;
    private String keySecondsLastRequest;

    public HomeActivityModule(ViewInterface view, String locale, String apiKey, String keySecondsLastRequest) {
        this.view = view;
        this.locale = locale;
        this.apiKey = apiKey;
        this.keySecondsLastRequest = keySecondsLastRequest;
    }

    @Provides
    @HomeActivityScopeInterface
    GetAllChampionsUseCase provideGetAllChampionsUseCase(@Named("championRepository") ChampionRepositoryInterface championRepositoryInterface) {
        return new GetAllChampionsUseCase(
                this.view,
                championRepositoryInterface
        );
    }

    @Provides
    @HomeActivityScopeInterface
    @Named("championRepository")
    ChampionRepositoryInterface provideChampionRepositoryInterface(ChampionClientServiceInterface championClientInterface, ChampionDBRepository championDBRepository, SharedPreferences sharedPreferences) {
        return new ChampionRepository(
                championClientInterface,
                this.locale,
                this.apiKey,
                championDBRepository,
                sharedPreferences,
                this.keySecondsLastRequest
        );
    }

    @Provides
    @HomeActivityScopeInterface
    @Named("championMockRepository")
    ChampionRepositoryInterface provideChampionMockRepositoryInterface(ChampionDBRepository championDBRepository, SharedPreferences sharedPreferences) {
        return new ChampionMockRepository(championDBRepository, sharedPreferences, this.keySecondsLastRequest);
    }

    @Provides
    @HomeActivityScopeInterface
    ChampionDBRepository provideChampionDBRepository(Realm realm) {
        return new ChampionDBRepository(realm);
    }

    @Provides
    @HomeActivityScopeInterface
    ChampionClientServiceInterface provideChampionClientInterface(@Named("clientRito") Retrofit retrofit) {
        return retrofit.create(ChampionClientServiceInterface.class);
    }
}
