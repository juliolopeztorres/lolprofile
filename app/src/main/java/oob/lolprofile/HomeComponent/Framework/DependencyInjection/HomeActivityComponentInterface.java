package oob.lolprofile.HomeComponent.Framework.DependencyInjection;


import dagger.Component;
import oob.lolprofile.ApplicationComponent.DependencyInjection.BaseApplicationComponentInterface;
import oob.lolprofile.HomeComponent.Framework.HomeActivity;

@HomeActivityScopeInterface
@Component(modules = HomeActivityModule.class, dependencies = BaseApplicationComponentInterface.class)
public interface HomeActivityComponentInterface {
    void inject(HomeActivity homeActivity);
}
