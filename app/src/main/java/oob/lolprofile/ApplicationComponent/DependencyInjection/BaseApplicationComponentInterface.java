package oob.lolprofile.ApplicationComponent.DependencyInjection;

import javax.inject.Named;

import dagger.Component;
import retrofit2.Retrofit;

@BaseApplicationScopeInterface
@Component(modules = {ClientRitoModule.class, ClientChampionModule.class})
public interface BaseApplicationComponentInterface {
    @Named("clientRito") Retrofit getClientRito();

    @Named("clientChampion") Retrofit getClientChampion();
}
