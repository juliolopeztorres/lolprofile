package oob.lolprofile.DetailsComponent.Framework.Adapter;

import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

class ChampionViewHolder {
    private RoundedImageView championAvatar;
    private TextView textViewChampionName;
    private TextView textViewChampionWinRateWins;

    TextView getTextViewChampionName() {
        return textViewChampionName;
    }

    ChampionViewHolder setTextViewChampionName(TextView textViewChampionName) {
        this.textViewChampionName = textViewChampionName;
        return this;
    }

    TextView getTextViewChampionWinRateWins() {
        return textViewChampionWinRateWins;
    }

    ChampionViewHolder setTextViewChampionWinRateWins(TextView textViewChampionWinRateWins) {
        this.textViewChampionWinRateWins = textViewChampionWinRateWins;
        return this;
    }

    RoundedImageView getChampionAvatar() {
        return championAvatar;
    }

    ChampionViewHolder setChampionAvatar(RoundedImageView championAvatar) {
        this.championAvatar = championAvatar;
        return this;
    }
}
