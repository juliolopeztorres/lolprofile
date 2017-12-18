package oob.lolprofile.ApplicationComponent.DependencyInjection;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module
public class PreferencesModule {
    private Context context;
    private String preferencesName;

    public PreferencesModule(Context context, String preferencesName) {
        this.context = context;
        this.preferencesName = preferencesName;
    }

    @Provides
    @BaseApplicationScopeInterface
    SharedPreferences providePreferences() {
        return this.context.getSharedPreferences(this.preferencesName, MODE_PRIVATE);
    }
}
