package oob.lolprofile.DetailsComponent.Framework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import oob.lolprofile.DetailsComponent.Domain.Model.Counter;
import oob.lolprofile.HomeComponent.Domain.Model.Champion;
import oob.lolprofile.R;
import oob.lolprofile.Util.DoubleOperation;

public class ChampionCounterAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Champion> champions;
    private ArrayList<Counter> counters;
    private int layout;
    private OnChampionEvents eventsManagement;

    public ChampionCounterAdapter(Context context, ArrayList<Champion> champions, ArrayList<Counter> counters, int layout, OnChampionEvents eventsManagement) {
        this.context = context;
        this.champions = champions;
        this.counters = counters;
        this.layout = layout;
        this.eventsManagement = eventsManagement;
    }

    @Override
    public int getCount() {
        return this.counters.size();
    }

    @Override
    public Object getItem(int i) {
        return this.counters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.inflateView(view);

        ChampionViewHolder championViewHolder = (ChampionViewHolder) view.getTag();

        Counter counter = this.counters.get(i);
        final Champion champion = Champion.findById(this.champions, counter.getId());

        assert champion != null;
        Picasso.with(this.context)
                .load(String.format(this.context.getString(R.string.base_url_champion_image), champion.getImage()))
                .placeholder(R.drawable.champion_placeholder)
                .centerCrop()
                .fit()
                .into(championViewHolder.getChampionAvatar());

        championViewHolder.getChampionAvatar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventsManagement.onClick(champions, champion);
            }
        });

        championViewHolder.getTextViewChampionName().setText(champion.getName());
        championViewHolder.getTextViewChampionWinRateWins().setText(
                String.format(
                        this.context.getResources().getString(R.string.counter_champion_rate_wins),
                        DoubleOperation.roundDoubleToString(counter.getWinRate(), 1),
                        String.valueOf(counter.getWins())
                )
        );

        return view;
    }

    private View inflateView(View view) {
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(this.layout, null);
            view.setTag(this.createViewHolder(view));
        }

        return view;
    }

    private ChampionViewHolder createViewHolder(View view) {
        return (new ChampionViewHolder())
                .setChampionAvatar((RoundedImageView) view.findViewById(R.id.roundImageViewChampionAvatar))
                .setTextViewChampionName((TextView) view.findViewById(R.id.textViewChampionName))
                .setTextViewChampionWinRateWins((TextView) view.findViewById(R.id.textViewChampionWinRateWins))
                ;
    }

    public interface OnChampionEvents {
        void onClick(ArrayList<Champion> champions, Champion championClicked);
    }
}
