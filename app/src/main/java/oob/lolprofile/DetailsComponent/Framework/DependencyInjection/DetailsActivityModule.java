package oob.lolprofile.DetailsComponent.Framework.DependencyInjection;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import oob.lolprofile.DetailsComponent.Data.CounterChampionClientServiceInterface;
import oob.lolprofile.DetailsComponent.Data.CounterChampionRepository;
import oob.lolprofile.DetailsComponent.Domain.CounterChampionRepositoryInterface;
import oob.lolprofile.DetailsComponent.Domain.GetCounterChampionsByChampionIdUseCase;
import oob.lolprofile.DetailsComponent.Domain.ViewInterface;
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
}
