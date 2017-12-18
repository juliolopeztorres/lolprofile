package oob.lolprofile.HomeComponent.Framework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import oob.lolprofile.ApplicationComponent.BaseApplication;
import oob.lolprofile.HomeComponent.Domain.Model.Champion;
import oob.lolprofile.HomeComponent.Domain.GetAllChampionsUseCase;
import oob.lolprofile.HomeComponent.Domain.ViewInterface;
import oob.lolprofile.HomeComponent.Framework.Adapter.ChampionAdapter;
import oob.lolprofile.HomeComponent.Framework.DependencyInjection.DaggerHomeActivityComponentInterface;
import oob.lolprofile.HomeComponent.Framework.DependencyInjection.HomeActivityComponentInterface;
import oob.lolprofile.HomeComponent.Framework.DependencyInjection.HomeActivityModule;
import oob.lolprofile.R;

public class HomeActivity extends AppCompatActivity implements ViewInterface {

    HomeActivityComponentInterface component;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.gridViewChampions)
    GridView gridView;

    @BindView(R.id.progressBarChampionsResume)
    ProgressBar progressBar;

    @BindView(R.id.imageViewSadFace)
    ImageView imageViewSadFace;

    @Inject
    GetAllChampionsUseCase getAllChampionsUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        this.toolbar.setTitle(getString(R.string.activity_home_title));
        this.setSupportActionBar(this.toolbar);

        this.component = DaggerHomeActivityComponentInterface.builder()
                .baseApplicationComponentInterface(((BaseApplication) this.getApplication()).getComponent())
                .homeActivityModule(
                        new HomeActivityModule(
                                this,
                                Locale.getDefault().toString(),
                                getString(R.string.api_key_rito),
                                getString(R.string.key_seconds_last_request)
                        ))
                .build();
        this.component.inject(this);

        this.getAllChampionsUseCase.getAll();
    }

    @Override
    public void showChampions(ArrayList<Champion> champions) {
        ChampionAdapter championAdapter = new ChampionAdapter(this, champions, R.layout.grid_champion_item);
        this.gridView.setAdapter(championAdapter);
        this.progressBar.setVisibility(View.GONE);
        this.imageViewSadFace.setVisibility(View.GONE);
        this.gridView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        this.progressBar.setVisibility(View.GONE);
        this.imageViewSadFace.setVisibility(View.VISIBLE);
    }
}
