package oob.lolprofile.HomeComponent.Framework.Fragment.DependencyInjection;

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
public class ChampionsFragmentModule {
    private ViewInterface view;
    private String locale;
    private String apiKey;
    private String keySecondsLastRequest;

    public ChampionsFragmentModule(ViewInterface view, String locale, String apiKey, String keySecondsLastRequest) {
        this.view = view;
        this.locale = locale;
        this.apiKey = apiKey;
        this.keySecondsLastRequest = keySecondsLastRequest;
    }

    @Provides
    @ChampionsFragmentScopeInterface
    GetAllChampionsUseCase provideGetAllChampionsUseCase(@Named("championRepository") ChampionRepositoryInterface championRepositoryInterface) {
        return new GetAllChampionsUseCase(
                this.view,
                championRepositoryInterface
        );
    }

    @Provides
    @ChampionsFragmentScopeInterface
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
    @ChampionsFragmentScopeInterface
    @Named("championMockRepository")
    ChampionRepositoryInterface provideChampionMockRepositoryInterface(ChampionDBRepository championDBRepository, SharedPreferences sharedPreferences) {
        return new ChampionMockRepository(championDBRepository, sharedPreferences, this.keySecondsLastRequest);
    }

    @Provides
    @ChampionsFragmentScopeInterface
    ChampionDBRepository provideChampionDBRepository(Realm realm) {
        return new ChampionDBRepository(realm);
    }

    @Provides
    @ChampionsFragmentScopeInterface
    ChampionClientServiceInterface provideChampionClientInterface(@Named("clientRito") Retrofit retrofit) {
        return retrofit.create(ChampionClientServiceInterface.class);
    }
}
