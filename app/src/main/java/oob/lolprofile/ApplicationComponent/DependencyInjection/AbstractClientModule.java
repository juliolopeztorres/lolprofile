package oob.lolprofile.ApplicationComponent.DependencyInjection;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = {LogModule.class, CacheModule.class})
public class AbstractClientModule {
    private static final int BACKEND_TIMEOUT = 60;

    @Provides
    @BaseApplicationScopeInterface
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .connectTimeout(BACKEND_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(BACKEND_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(BACKEND_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
