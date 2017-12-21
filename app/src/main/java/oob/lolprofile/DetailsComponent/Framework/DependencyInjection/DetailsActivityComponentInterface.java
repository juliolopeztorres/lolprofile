package oob.lolprofile.DetailsComponent.Framework.DependencyInjection;

import dagger.Component;
import oob.lolprofile.ApplicationComponent.DependencyInjection.BaseApplicationComponentInterface;
import oob.lolprofile.DetailsComponent.Framework.DetailsActivity;

@DetailsActivityScopeInterface
@Component(modules = DetailsActivityModule.class, dependencies = BaseApplicationComponentInterface.class)
public interface DetailsActivityComponentInterface {
    void inject(DetailsActivity detailsActivity);
}
