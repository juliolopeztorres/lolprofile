package oob.lolprofile.ApplicationComponent;

import android.app.Application;
import android.content.SharedPreferences;

import oob.lolprofile.ApplicationComponent.DependencyInjection.BaseApplicationComponentInterface;
import oob.lolprofile.ApplicationComponent.DependencyInjection.CacheModule;
import oob.lolprofile.ApplicationComponent.DependencyInjection.ClientChampionModule;
import oob.lolprofile.ApplicationComponent.DependencyInjection.ClientRitoModule;
import oob.lolprofile.ApplicationComponent.DependencyInjection.DaggerBaseApplicationComponentInterface;
import oob.lolprofile.ApplicationComponent.DependencyInjection.LocalDBModule;
import oob.lolprofile.ApplicationComponent.DependencyInjection.PreferencesModule;
import oob.lolprofile.R;
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
                .localDBModule(new LocalDBModule(this))
                .preferencesModule(new PreferencesModule(this, getString(R.string.shared_preferences_name)))
                .build();

        this.setDefaultValueForKey(getString(R.string.elo_default_key), getString(R.string.key_default_stored_elo));
        this.setDefaultValueForKey(getString(R.string.row_number_default_key), getString(R.string.key_default_stored_row_number));
    }

    private void setDefaultValueForKey(String defaultValue, String key) {
        SharedPreferences sharedPreferences = this.component.getPreferences();
        String value = sharedPreferences.getString(key, getString(R.string.string_key_not_found));
        if (value.equals(getString(R.string.string_key_not_found))) {
            sharedPreferences.edit().putString(key, defaultValue).apply();
        }
    }

    public BaseApplicationComponentInterface getComponent() {
        return component;
    }
}
