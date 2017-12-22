package oob.lolprofile.HomeComponent.Framework.Fragment.Option.DependencyInjection;

import dagger.Component;
import oob.lolprofile.ApplicationComponent.DependencyInjection.BaseApplicationComponentInterface;
import oob.lolprofile.HomeComponent.Framework.Fragment.Option.OptionsFragment;

@OptionsFragmentScopeInterface
@Component(modules = OptionsFragmentModule.class, dependencies = BaseApplicationComponentInterface.class)
public interface OptionsFragmentComponentInterface {
    void inject(OptionsFragment optionsFragment);
}
