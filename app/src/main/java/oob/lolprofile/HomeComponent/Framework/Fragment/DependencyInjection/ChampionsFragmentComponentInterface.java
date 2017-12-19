package oob.lolprofile.HomeComponent.Framework.Fragment.DependencyInjection;


import dagger.Component;
import oob.lolprofile.ApplicationComponent.DependencyInjection.BaseApplicationComponentInterface;
import oob.lolprofile.HomeComponent.Framework.Fragment.ChampionsFragment;

@ChampionsFragmentScopeInterface
@Component(modules = ChampionsFragmentModule.class, dependencies = BaseApplicationComponentInterface.class)
public interface ChampionsFragmentComponentInterface {
    void inject(ChampionsFragment championsFragment);
}
