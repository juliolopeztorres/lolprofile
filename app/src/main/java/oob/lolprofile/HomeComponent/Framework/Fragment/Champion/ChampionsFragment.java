package oob.lolprofile.HomeComponent.Framework.Fragment.Champion;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.GetAllChampionsUseCase;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.ViewInterface;
import oob.lolprofile.HomeComponent.Framework.Fragment.Champion.Adapter.ChampionAdapter;
import oob.lolprofile.HomeComponent.Framework.Fragment.Champion.DependencyInjection.ChampionsFragmentComponentInterface;
import oob.lolprofile.HomeComponent.Framework.Fragment.Champion.DependencyInjection.ChampionsFragmentModule;
import oob.lolprofile.HomeComponent.Framework.Fragment.Champion.DependencyInjection.DaggerChampionsFragmentComponentInterface;
import oob.lolprofile.R;

public class ChampionsFragment extends Fragment implements ViewInterface {

    ChampionsFragmentComponentInterface component;

    private Context context;
    private ChampionAdapter.OnChampionEvents eventsManagement;

    @BindView(R.id.gridViewChampions)
    GridView gridView;

    @BindView(R.id.progressBarChampionsResume)
    ProgressBar progressBar;

    @BindView(R.id.imageViewSadFace)
    ImageView imageViewSadFace;

    @Inject
    GetAllChampionsUseCase getAllChampionsUseCase;

    private ChampionAdapter championAdapter;

    public ChampionsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        this.eventsManagement = (ChampionAdapter.OnChampionEvents) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champions, container, false);
        this.context = view.getContext();
        ButterKnife.bind(this, view);

        this.component = DaggerChampionsFragmentComponentInterface.builder()
                .baseApplicationComponentInterface(((BaseApplication) this.context.getApplicationContext()).getComponent())
                .championsFragmentModule(
                        new ChampionsFragmentModule(
                                this,
                                Locale.getDefault().toString(),
                                getString(R.string.api_key_rito),
                                getString(R.string.key_seconds_last_request)
                        ))
                .build();
        this.component.inject(this);

        this.showLoading();
        this.getAllChampionsUseCase.getAll();
        return view;
    }

    @Override
    public void showChampions(ArrayList<Champion> champions) {
        this.championAdapter = new ChampionAdapter(this.context, champions, R.layout.grid_champion_item, this.eventsManagement);
        this.gridView.setAdapter(this.championAdapter);
        this.hideLoading();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this.context, text, Toast.LENGTH_LONG).show();
        this.progressBar.setVisibility(View.GONE);
        this.imageViewSadFace.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        this.showError(getString(R.string.message_data_not_found_in_server));
    }

    public void filterChampsByName(String champName, boolean orderingAscending) {
        if (this.championAdapter == null) {
            return;
        }
        this.championAdapter.filterByName(champName, orderingAscending);
    }

    public void showLoading() {
        this.progressBar.setVisibility(View.VISIBLE);
        this.imageViewSadFace.setVisibility(View.GONE);
        this.gridView.setVisibility(View.GONE);
    }

    private void hideLoading() {
        this.progressBar.setVisibility(View.GONE);
        this.imageViewSadFace.setVisibility(View.GONE);
        this.gridView.setVisibility(View.VISIBLE);
    }

    public void orderChampions() {
        if (this.championAdapter == null) {
            return;
        }
        this.championAdapter.reverseOrderChampions();
    }
}
