package oob.lolprofile.ApplicationComponent;

import android.app.Application;

import oob.lolprofile.ApplicationComponent.DependencyInjection.BaseApplicationComponentInterface;
import oob.lolprofile.ApplicationComponent.DependencyInjection.CacheModule;
import oob.lolprofile.ApplicationComponent.DependencyInjection.ClientChampionModule;
import oob.lolprofile.ApplicationComponent.DependencyInjection.ClientRitoModule;
import oob.lolprofile.ApplicationComponent.DependencyInjection.DaggerBaseApplicationComponentInterface;
import oob.lolprofile.R;
import retrofit2.Retrofit;
import timber.log.Timber;

public class BaseApplication extends Application {
    BaseApplicationComponentInterface component;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        // ------------ DAGGER - DI -------------- //;
        this.component = DaggerBaseApplicationComponentInterface.builder()
                .cacheModule(
                        new CacheModule(
                                getCacheDir(),
                                getResources().getString(R.string.cache_name),
                                getResources().getInteger(R.integer.cache_size)
                        )
                )
                .clientRitoModule(new ClientRitoModule(getString(R.string.base_url_rito)))
                .clientChampionModule(new ClientChampionModule(getString(R.string.base_url_champion_gg)))
                .build();
    }

    public BaseApplicationComponentInterface getComponent() {
        return component;
    }
}
