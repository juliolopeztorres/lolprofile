package oob.lolprofile.ApplicationComponent.DependencyInjection;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import oob.lolprofile.BuildConfig;
import timber.log.Timber;

@Module
public class LogModule {

    @Provides
    @BaseApplicationScopeInterface
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        return httpLoggingInterceptor;
    }
}
