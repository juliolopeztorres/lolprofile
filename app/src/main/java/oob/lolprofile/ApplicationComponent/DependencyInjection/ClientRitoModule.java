package oob.lolprofile.ApplicationComponent.DependencyInjection;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import oob.lolprofile.HomeComponent.Data.Mapper.ChampionResponseMapper;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;
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
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder().registerTypeAdapter(new TypeToken<ArrayList<Champion>>() {
                                }.getType(), new ChampionResponseMapper()).create()
                        )
                )
                .baseUrl(this.baseUrl)
                .client(client)
                .build();
    }
}
