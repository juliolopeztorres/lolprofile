package oob.lolprofile.ApplicationComponent.DependencyInjection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class LocalDBModule {

    private Context context;

    public LocalDBModule(Context context) {
        this.context = context;
    }

    @Provides
    @BaseApplicationScopeInterface
    Realm provideLocalDB() {
        Realm.init(this.context);
        Realm.setDefaultConfiguration(new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build());
        return Realm.getDefaultInstance();
    }
}
