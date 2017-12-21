package oob.lolprofile.HomeComponent.Framework;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import oob.lolprofile.DetailsComponent.Framework.DetailsActivity;
import oob.lolprofile.HomeComponent.Domain.Model.Champion;
import oob.lolprofile.HomeComponent.Framework.Fragment.Adapter.ChampionAdapter;
import oob.lolprofile.HomeComponent.Framework.Fragment.ChampionsFragment;
import oob.lolprofile.R;

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener, ChampionAdapter.OnChampionEvents {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    private ChampionsFragment championsFragment = new ChampionsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        this.setSupportActionBar(this.toolbar);
        this.setHomeButton();
        this.setEventListeners();

        this.setDefaultFragment();
    }

    private void setEventListeners() {
        this.navigationView.setNavigationItemSelectedListener(this);
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
        MenuItem menuItemSearch = menu.findItem(R.id.search);
        SearchView viewSearch = (SearchView) menuItemSearch.getActionView();
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

    private void updateFragmentView(MenuItem item, Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction().replace(R.id.relativeLayoutMainContent, fragment).commit();
        ActionBar actionBar = this.getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(item.getTitle());
        item.setChecked(true);
        this.drawerLayout.closeDrawers();
    }

    private void setDefaultFragment() {
        this.updateFragmentView(this.navigationView.getMenu().getItem(0), this.championsFragment);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String champName) {
        this.championsFragment.filterChampsByName(champName);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = this.getFragment(item.getItemId());

        if (fragment == null) {
            return false;
        }
        updateFragmentView(item, fragment);
        return true;
    }

    private Fragment getFragment(int itemId) {
        switch (itemId) {
            case R.id.menu_champions:
                return this.championsFragment;
            case R.id.menu_summoner:
            case R.id.menu_options:
                this.drawerLayout.closeDrawers();
                return null;
            default:
                return null;
        }
    }

    @Override
    public void onClick(ArrayList<Champion> champions, Champion championClicked) {
        this.championsFragment.showLoading();
        Intent it = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsActivity.KEY_CHAMPIONS, champions);
        bundle.putSerializable(DetailsActivity.KEY_CHAMPION_CLICKED, championClicked);
        it.putExtras(bundle);
        startActivity(it);
    }
}
