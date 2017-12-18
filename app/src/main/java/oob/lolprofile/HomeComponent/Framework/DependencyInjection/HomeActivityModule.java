package oob.lolprofile.HomeComponent.Framework.DependencyInjection;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import oob.lolprofile.HomeComponent.Data.ChampionClientInterface;
import oob.lolprofile.HomeComponent.Data.ChampionRepository;
import oob.lolprofile.HomeComponent.Domain.ChampionRepositoryInterface;
import oob.lolprofile.HomeComponent.Domain.GetAllChampionsUseCase;
import oob.lolprofile.HomeComponent.Domain.ViewInterface;
import retrofit2.Retrofit;

@Module
public class HomeActivityModule {
    private ViewInterface view;
    private String locale;
    private String apiKey;

    public HomeActivityModule(ViewInterface view, String locale, String apiKey) {
        this.view = view;
        this.locale = locale;
        this.apiKey = apiKey;
    }

    @Provides
    @HomeActivityScopeInterface
    GetAllChampionsUseCase provideGetAllChampionsUseCase(ChampionRepositoryInterface championRepositoryInterface) {
        return new GetAllChampionsUseCase(
                this.view,
                championRepositoryInterface
        );
    }

    @Provides
    @HomeActivityScopeInterface
    ChampionRepositoryInterface provideChampionRepositoryInterface(ChampionClientInterface championClientInterface) {
        return new ChampionRepository(championClientInterface, this.locale, this.apiKey);
    }

    @Provides
    @HomeActivityScopeInterface
    ChampionClientInterface provideChampionClientInterface(@Named("clientRito") Retrofit retrofit) {
        return retrofit.create(ChampionClientInterface.class);
    }
}
