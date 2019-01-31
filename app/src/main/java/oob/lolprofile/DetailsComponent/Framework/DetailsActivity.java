package oob.lolprofile.DetailsComponent.Framework;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.ChampionRoleCounter;
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.GetCounterChampionsByChampionIdUseCase;
import oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model.Counter;
import oob.lolprofile.DetailsComponent.Domain.DefaultELO.GetDefaultELOUseCase;
import oob.lolprofile.DetailsComponent.Domain.DefaultRowNumber.GetDefaultRowNumberUseCase;
import oob.lolprofile.DetailsComponent.Domain.GetAllChampions.GetAllChampionsUseCase;
import oob.lolprofile.DetailsComponent.Framework.Adapter.ChampionCounterAdapter;
import oob.lolprofile.DetailsComponent.Framework.DependencyInjection.DaggerDetailsActivityComponentInterface;
import oob.lolprofile.DetailsComponent.Framework.DependencyInjection.DetailsActivityComponentInterface;
import oob.lolprofile.DetailsComponent.Framework.DependencyInjection.DetailsActivityModule;
import oob.lolprofile.DetailsComponent.Framework.DependencyInjection.DetailsActivityViewInterface;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;
import oob.lolprofile.R;
import oob.lolprofile.Util.DoubleOperation;
import oob.lolprofile.Util.ExpandableHeightGridView;
import oob.lolprofile.Util.RoleNamesParser;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityViewInterface, TabLayout.OnTabSelectedListener, ChampionCounterAdapter.OnChampionEvents {

    public static final String KEY_CHAMPION_CLICKED = "championClicked";

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
    @BindView(R.id.textViewNoBadAgainstChampInfo)
    TextView textViewNoBadAgainstChampInfo;
    @BindView(R.id.textViewNoGoodAgainstChampInfo)
    TextView textViewNoGoodAgainstChampInfo;
    @BindView(R.id.textViewEnemyTips)
    TextView textViewEnemyTips;
    @BindView(R.id.textViewAllyTips)
    TextView textViewAllyTips;
    @BindView(R.id.textViewEnemyTipsLabel)
    TextView textViewEnemyTipsLabel;
    @BindView(R.id.textViewAllyTipsLabel)
    TextView textViewAllyTipsLabel;

    @Inject
    GetCounterChampionsByChampionIdUseCase getCounterChampionsByChampionIdUseCase;
    @Inject
    GetDefaultELOUseCase getDefaultELOUseCase;
    @Inject
    GetAllChampionsUseCase getAllChampionsUseCase;
    @Inject
    GetDefaultRowNumberUseCase getDefaultRowNumberUseCase;

    private int rowCounters;
    private ArrayList<ChampionRoleCounter> championRoleCounters;
    private ArrayList<Champion> champions;
    private Champion championClicked;
    private String[] elo_keys;
    private String defaultELO;
    private int rowNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        this.component = DaggerDetailsActivityComponentInterface.builder()
                .baseApplicationComponentInterface(((BaseApplication) this.getApplication()).getComponent())
                .detailsActivityModule(new DetailsActivityModule(this, getString(R.string.api_key_champion), getString(R.string.key_default_stored_elo), getString(R.string.key_default_stored_row_number)))
                .build();
        this.component.inject(this);

        this.elo_keys = getResources().getStringArray(R.array.elo_keys);
        this.defaultELO = this.getDefaultELOUseCase.getDefaultELO();
        this.rowNumber = this.getDefaultRowNumberUseCase.getDefaultRowNumber();

        if (!this.recoverParamsFromBundle()) {
            this.showError(getString(R.string.message_data_not_found_in_bundle));
            this.finish();
            return;
        }

        this.setChampionInfo();
        this.getAllChampionsUseCase.getAll();
    }

    private boolean recoverParamsFromBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return false;
        }
        this.championClicked = ((Champion) bundle.getSerializable(KEY_CHAMPION_CLICKED));

        return this.championClicked != null;
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

        if (actionBar == null) {
            return;
        }
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details_activity, menu);
        menu.getItem(this.getELOKeyPosition(this.defaultELO)).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String eloKey;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_details_bronze:
                eloKey = getString(R.string.elo_key_bronze);
                break;
            case R.id.menu_details_silver:
                eloKey = getString(R.string.elo_key_silver);
                break;
            case R.id.menu_details_gold:
                eloKey = getString(R.string.elo_key_gold);
                break;
            case R.id.menu_details_platinum:
                eloKey = getString(R.string.elo_key_platinum);
                break;
            case R.id.menu_details_high_elo:
                eloKey = getString(R.string.elo_key_high_elo);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(this.championClicked.getId(), eloKey);
        item.setChecked(true);
        return true;
    }

    private void setChampionInfo() {
        toolbar.setTitle(this.championClicked.getName());
        this.setChampionSplash();

        this.textViewChampionDescription.setText(this.ucword(this.championClicked.getTitle()));
        this.textViewLore.setText(this.championClicked.getLore());
        this.textViewEnemyTips.setText(this.implode(this.championClicked.getEnemyTips(), " "));
        this.textViewAllyTips.setText(this.implode(this.championClicked.getAllyTips(), " "));

        if (this.textViewEnemyTips.getText().toString().isEmpty()) {
            this.textViewEnemyTipsLabel.setVisibility(View.GONE);
        }

        if (this.textViewAllyTips.getText().toString().isEmpty()) {
            this.textViewAllyTipsLabel.setVisibility(View.GONE);
        }
    }

    private String ucword(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }

    private String implode(ArrayList<String> arrayList, String glue) {
        StringBuilder result = new StringBuilder();

        for(String part: arrayList) {
            result.append(part).append(glue);
        }

        return result.toString();
    }

    private void setChampionSplash() {
        int randomSkinNumber = (new Random()).nextInt(this.championClicked.getSkins().size());
        Picasso.with(this)
                .load(
                        String.format(
                                getString(R.string.base_url_champion_splash),
                                this.championClicked.getKey(),
                                String.valueOf(this.championClicked.getSkins().get(randomSkinNumber).getNum()))
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
    public void showChampions(ArrayList<Champion> champions) {
        this.champions = champions;

        this.rowCounters = this.rowNumber * getResources().getInteger(R.integer.grid_view_columns_counters);
        this.tabLayout.addOnTabSelectedListener(this);
        this.setSupportActionBar(this.toolbar);
        this.setBackButton();

        this.getCounterChampionsByChampionIdUseCase.getCountersByChampionId(this.championClicked.getId(), this.defaultELO);
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        this.showError(getString(R.string.message_data_not_found_in_server_details));
    }

    private void setTabLabels(ArrayList<String> tabLabels) {
        this.tabLayout.removeAllTabs();
        for (String tabLabel : tabLabels) {
            TabLayout.Tab tab = this.tabLayout.newTab();
            tab.setText(tabLabel);
            this.tabLayout.addTab(tab);
        }
    }

    private void setChampionStats(String role) {
        ChampionRoleCounter championRoleCounter = ChampionRoleCounter.getStatsByRole(this.championRoleCounters, role);
        if (championRoleCounter == null) {
            return;
        }
        this.textViewChampionKDA.setText(
                String.format(
                        getString(R.string.champion_kda),
                        DoubleOperation.roundDoubleToString(championRoleCounter.getKills(), 2),
                        DoubleOperation.roundDoubleToString(championRoleCounter.getDeaths(), 2),
                        DoubleOperation.roundDoubleToString(championRoleCounter.getAssists(), 2)
                )
        );
        this.textViewChampionKDA.setVisibility(View.VISIBLE);

        this.textViewChampionWinRate.setText(
                String.format(getString(R.string.champion_win_rate), DoubleOperation.roundDoubleToString(championRoleCounter.getWinRate(), 1, true))
        );
        this.textViewChampionWinRate.setVisibility(View.VISIBLE);

        this.textViewChampionWinGames.setText(
                String.format(getString(R.string.champion_games), String.valueOf(championRoleCounter.getGamesPlayed()))
        );
        this.textViewChampionWinGames.setVisibility(View.VISIBLE);
    }

    public void showCounters(String role) {
        ArrayList<Counter> championCounters = ChampionRoleCounter.getCountersByRole(this.championRoleCounters, role, this.championClicked.getId());
        if (championCounters == null) {
            championCounters = new ArrayList<>();
        }

        ArrayList<Counter> championBadAgainstFiltered = Counter.filter(championCounters, 0, rowCounters, false);
        this.showChampionsInGrid(championBadAgainstFiltered, this.gridViewCounterChampions);

        ArrayList<Counter> championGoodAgainstFiltered = Counter.filter(championCounters, championCounters.size() - rowCounters, championCounters.size(), true);
        Collections.reverse(championGoodAgainstFiltered);
        this.showChampionsInGrid(championGoodAgainstFiltered, this.gridViewGoodAgainstChampions);

        this.textViewNoBadAgainstChampInfo.setVisibility(View.GONE);
        if (championBadAgainstFiltered.isEmpty()) {
            this.textViewNoBadAgainstChampInfo.setText(String.format(getString(R.string.textView_noInfo_badAgainst), this.championClicked.getName()));
            this.textViewNoBadAgainstChampInfo.setVisibility(View.VISIBLE);
        }

        this.textViewNoGoodAgainstChampInfo.setVisibility(View.GONE);
        if (championGoodAgainstFiltered.isEmpty()) {
            this.textViewNoGoodAgainstChampInfo.setText(String.format(getString(R.string.textView_noInfo_goodAgainst), this.championClicked.getName()));
            this.textViewNoGoodAgainstChampInfo.setVisibility(View.VISIBLE);
        }
    }

    private void showChampionsInGrid(ArrayList<Counter> championsToShow, ExpandableHeightGridView gridView) {
        ChampionCounterAdapter championCounterAdapter = new ChampionCounterAdapter(
                this,
                this.champions,
                championsToShow,
                R.layout.grid_champion_details_item,
                this
        );
        gridView.setAdapter(championCounterAdapter);
        gridView.setExpanded(true);
    }

    @Override
    public void onClick(Champion championClicked) {
        Intent it = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsActivity.KEY_CHAMPION_CLICKED, championClicked);
        it.putExtras(bundle);
        it.setFlags(it.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(it);
    }

    private int getELOKeyPosition(String elo) {
        int length = this.elo_keys.length;
        for(int i = 0; i < length; i++) {
            if (this.elo_keys[i].equals(elo)) {
                return i;
            }
        }

        return -1;
    }
}
