package oob.lolprofile.ApplicationComponent.DependencyInjection;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AbstractClientModule.class)
public class ClientRitoModule {
    private final String baseUrl;

    public ClientRitoModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @BaseApplicationScopeInterface
    @Named("clientRito")
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(this.baseUrl)
                .client(client)
                .build();
    }
}
