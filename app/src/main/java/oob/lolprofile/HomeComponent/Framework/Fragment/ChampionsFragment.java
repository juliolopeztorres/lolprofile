package oob.lolprofile.HomeComponent.Framework.Fragment;


import android.content.Context;
import android.os.Bundle;
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
import oob.lolprofile.HomeComponent.Domain.GetAllChampionsUseCase;
import oob.lolprofile.HomeComponent.Domain.Model.Champion;
import oob.lolprofile.HomeComponent.Domain.ViewInterface;
import oob.lolprofile.HomeComponent.Framework.Fragment.Adapter.ChampionAdapter;
import oob.lolprofile.HomeComponent.Framework.Fragment.DependencyInjection.ChampionsFragmentComponentInterface;
import oob.lolprofile.HomeComponent.Framework.Fragment.DependencyInjection.ChampionsFragmentModule;
import oob.lolprofile.HomeComponent.Framework.Fragment.DependencyInjection.DaggerChampionsFragmentComponentInterface;
import oob.lolprofile.R;

public class ChampionsFragment extends Fragment implements ViewInterface {

    ChampionsFragmentComponentInterface component;

    private Context context;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        this.getAllChampionsUseCase.getAll();
        return view;
    }

    @Override
    public void showChampions(ArrayList<Champion> champions) {
        this.championAdapter = new ChampionAdapter(this.context, champions, R.layout.grid_champion_item);
        this.gridView.setAdapter(this.championAdapter);
        this.progressBar.setVisibility(View.GONE);
        this.imageViewSadFace.setVisibility(View.GONE);
        this.gridView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show();
        this.progressBar.setVisibility(View.GONE);
        this.imageViewSadFace.setVisibility(View.VISIBLE);
    }

    public void filterChampsByName(String champName) {
        this.championAdapter.filterByName(champName);
    }
}
