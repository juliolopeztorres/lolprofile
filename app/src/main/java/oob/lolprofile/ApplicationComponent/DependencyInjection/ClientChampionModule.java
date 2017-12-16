package oob.lolprofile.ApplicationComponent.DependencyInjection;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AbstractClientModule.class)
public class ClientChampionModule {
    private final String baseUrl;

    public ClientChampionModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @BaseApplicationScopeInterface
    @Named("clientChampion")
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(this.baseUrl)
                .client(client)
                .build();
    }
}
