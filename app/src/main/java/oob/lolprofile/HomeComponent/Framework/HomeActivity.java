package oob.lolprofile.HomeComponent.Framework;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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

public class HomeActivity extends AppCompatActivity implements ViewInterface, SearchView.OnQueryTextListener {

    HomeActivityComponentInterface component;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.gridViewChampions)
    GridView gridView;

    @BindView(R.id.progressBarChampionsResume)
    ProgressBar progressBar;

    @BindView(R.id.imageViewSadFace)
    ImageView imageViewSadFace;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Inject
    GetAllChampionsUseCase getAllChampionsUseCase;

    ChampionAdapter championAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        this.toolbar.setTitle(getString(R.string.activity_home_title));
        this.setSupportActionBar(this.toolbar);
        this.setHomeButton();

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

    private void setHomeButton() {
        ActionBar actionBar = this.getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView viewSearch = (SearchView) menuItem.getActionView();
        viewSearch.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.drawerLayout.openDrawer(Gravity.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showChampions(ArrayList<Champion> champions) {
        this.championAdapter = new ChampionAdapter(this, champions, R.layout.grid_champion_item);
        this.gridView.setAdapter(this.championAdapter);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String champName) {
        this.championAdapter.filterByName(champName);
        return true;
    }
}
