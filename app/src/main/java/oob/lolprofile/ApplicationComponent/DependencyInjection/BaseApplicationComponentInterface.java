package oob.lolprofile.ApplicationComponent.DependencyInjection;

import android.content.SharedPreferences;
import javax.inject.Named;
import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;

@BaseApplicationScopeInterface
@Component(modules = {ClientRitoModule.class, ClientChampionModule.class, LocalDBModule.class, PreferencesModule.class})
public interface BaseApplicationComponentInterface {
    @Named("clientRito") Retrofit getClientRito();

    @Named("clientChampion") Retrofit getClientChampion();

    Realm getLocalDB();

    SharedPreferences getPreferences();
}
