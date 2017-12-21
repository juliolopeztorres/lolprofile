package oob.lolprofile.DetailsComponent.Framework;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import oob.lolprofile.ApplicationComponent.BaseApplication;
import oob.lolprofile.DetailsComponent.Domain.Model.ChampionRoleCounter;
import oob.lolprofile.DetailsComponent.Domain.GetCounterChampionsByChampionIdUseCase;
import oob.lolprofile.DetailsComponent.Domain.Model.Counter;
import oob.lolprofile.DetailsComponent.Domain.ViewInterface;
import oob.lolprofile.DetailsComponent.Framework.Adapter.ChampionCounterAdapter;
import oob.lolprofile.DetailsComponent.Framework.DependencyInjection.DaggerDetailsActivityComponentInterface;
import oob.lolprofile.DetailsComponent.Framework.DependencyInjection.DetailsActivityComponentInterface;
import oob.lolprofile.DetailsComponent.Framework.DependencyInjection.DetailsActivityModule;
import oob.lolprofile.HomeComponent.Domain.ChampionRepositoryInterface;
import oob.lolprofile.HomeComponent.Domain.Model.Champion;
import oob.lolprofile.R;
import oob.lolprofile.Util.DoubleOperation;
import oob.lolprofile.Util.ExpandableHeightGridView;
import oob.lolprofile.Util.RoleNamesParser;

public class DetailsActivity extends AppCompatActivity implements ChampionRepositoryInterface.ChampionCallback, ViewInterface, TabLayout.OnTabSelectedListener {

    DetailsActivityComponentInterface component;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageViewChampionSplash)
    ImageView imageViewChampionSplash;
    @BindView(R.id.gridViewCounterChampions)
    ExpandableHeightGridView gridViewCounterChampions;
    @BindView(R.id.gridViewGoodAgainstChampions)
    ExpandableHeightGridView gridViewGoodAgainstChampions;
    @BindView(R.id.textViewChampionDescription)
    TextView textViewChampionDescription;
    @BindView(R.id.textViewLore)
    TextView textViewLore;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.textViewChampionKDA)
    TextView textViewChampionKDA;
    @BindView(R.id.textViewChampionWinRate)
    TextView textViewChampionWinRate;
    @BindView(R.id.textViewChampionWinGames)
    TextView textViewChampionWinGames;

    @Inject
    ChampionRepositoryInterface championRepository;
    @Inject
    GetCounterChampionsByChampionIdUseCase getCounterChampionsByChampionIdUseCase;

    private int rowCounters;
    private ArrayList<ChampionRoleCounter> championRoleCounters;
    private ArrayList<Champion> champions;
    private int championId = 1; // Annie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        this.component = DaggerDetailsActivityComponentInterface.builder()
                .baseApplicationComponentInterface(((BaseApplication) this.getApplication()).getComponent())
                .detailsActivityModule(new DetailsActivityModule(this, getString(R.string.api_key_champion)))
                .build();
        this.component.inject(this);

        this.rowCounters = getResources().getInteger(R.integer.grid_view_rows_counters) * getResources().getInteger(R.integer.grid_view_columns_counters);
        toolbar.setTitle("Annie");
        this.tabLayout.addOnTabSelectedListener(this);
        this.setSupportActionBar(this.toolbar);
        this.setBackButton();

        this.championRepository.getAll(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        String rol = RoleNamesParser.getChampionAPIKey(tab.getText() != null ? tab.getText().toString() : "");
        this.showCounters(rol);
        this.setChampionStats(rol);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    private void setBackButton() {
        ActionBar actionBar = this.getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_details_bronze:
                this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(this.championId, getString(R.string.elo_key_bronze));
                break;
            case R.id.menu_details_silver:
                this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(this.championId, getString(R.string.elo_key_silver));
                break;
            case R.id.menu_details_gold:
                this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(this.championId, getString(R.string.elo_key_gold));
                break;
            case R.id.menu_details_platinum:
                this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(this.championId, getString(R.string.elo_key_platinum));
                break;
            case R.id.menu_details_high_elo:
                this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(this.championId, getString(R.string.elo_key_high_elo));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        item.setChecked(true);
        return true;
    }

    @Override
    public void onSuccess(ArrayList<Champion> champions) {
        this.champions = champions;
        Champion annie = Champion.findById(this.champions, this.championId);
        assert annie != null;
        this.setChampionInfo(annie);

        this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(annie.getId(), getString(R.string.elo_default_key));
    }

    @Override
    public void onError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void setChampionInfo(Champion championInfo) {
        toolbar.setTitle(championInfo.getName());
        this.setChampionSplash(championInfo);

        this.textViewChampionDescription.setText(championInfo.getTitle());
        this.textViewLore.setText(championInfo.getLore());
    }

    private void setChampionSplash(Champion championInfo) {
        int randomSkinNumber = (new Random()).nextInt(championInfo.getSkins().size());
        Picasso.with(this)
                .load(
                        String.format(
                                getString(R.string.base_url_champion_splash),
                                championInfo.getName(),
                                String.valueOf(championInfo.getSkins().get(randomSkinNumber).getNum()))
                )
                .centerCrop()
                .fit()
                .into(this.imageViewChampionSplash);
    }

    @Override
    public void showCounters(ArrayList<ChampionRoleCounter> championRoleCounters) {
        if (championRoleCounters.size() == 0) {
            this.showError(getString(R.string.no_counters_found));
            return;
        }
        this.championRoleCounters = championRoleCounters;
        this.setTabLabels(ChampionRoleCounter.getChampRoles(this.championRoleCounters));
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void setTabLabels(ArrayList<String> tabLabels) {
        if (this.tabLayout.getTabCount() > 0) {
            TabLayout.Tab tabSelected = this.tabLayout.getTabAt(this.tabLayout.getSelectedTabPosition());
            assert tabSelected != null;

            String rol = RoleNamesParser.getChampionAPIKey(tabSelected.getText() != null ? tabSelected.getText().toString() : "");
            this.showCounters(rol);
            this.setChampionStats(rol);
        } else {
            this.tabLayout.removeAllTabs();

            for (String tabLabel : tabLabels) {
                TabLayout.Tab tab = this.tabLayout.newTab();
                tab.setText(tabLabel);
                this.tabLayout.addTab(tab);
            }
        }
    }

    private void setChampionStats(String role) {
        ChampionRoleCounter championRoleCounter = ChampionRoleCounter.getStatsByRole(this.championRoleCounters, role);
        assert championRoleCounter != null;
        this.textViewChampionKDA.setText(
                String.format(
                        getString(R.string.champion_kda),
                        DoubleOperation.roundDoubleToString(championRoleCounter.getKills(), 2),
                        DoubleOperation.roundDoubleToString(championRoleCounter.getDeaths(), 2),
                        DoubleOperation.roundDoubleToString(championRoleCounter.getAssists(), 2)
                )
        );

        this.textViewChampionWinRate.setText(
                String.format(getString(R.string.champion_win_rate), DoubleOperation.roundDoubleToString(championRoleCounter.getWinRate(), 2))
        );

        this.textViewChampionWinGames.setText(
                String.format(getString(R.string.champion_games), String.valueOf(championRoleCounter.getGamesPlayed()))
        );
    }

    public void showCounters(String role) {
        ArrayList<Counter> championCounters = ChampionRoleCounter.getCountersByRole(this.championRoleCounters, role);
        assert championCounters != null;
        this.showChampionsInGrid(Counter.filter(championCounters, 0, rowCounters), this.gridViewCounterChampions);

        ArrayList<Counter> championGoodAgainstFiltered = Counter.filter(championCounters, championCounters.size() - rowCounters, championCounters.size());
        Collections.reverse(championGoodAgainstFiltered);
        this.showChampionsInGrid(championGoodAgainstFiltered, this.gridViewGoodAgainstChampions);
    }

    private void showChampionsInGrid(ArrayList<Counter> championsToShow, ExpandableHeightGridView gridView) {
        ChampionCounterAdapter championCounterAdapter = new ChampionCounterAdapter(
                this,
                this.champions,
                championsToShow,
                R.layout.grid_champion_details_item
        );
        gridView.setAdapter(championCounterAdapter);
        gridView.setExpanded(true);
    }
}
