package oob.lolprofile.ApplicationComponent.DependencyInjection;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import oob.lolprofile.DetailsComponent.Data.Mapper.CounterChampionResponseMapper;
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;
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
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().registerTypeAdapter(new TypeToken<ArrayList<ChampionRoleCounter>>() {
                        }.getType(), new CounterChampionResponseMapper()).create()
                ))
                .baseUrl(this.baseUrl)
                .client(client)
                .build();
    }
}
