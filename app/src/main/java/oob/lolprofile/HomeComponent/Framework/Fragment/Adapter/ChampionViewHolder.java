package oob.lolprofile.HomeComponent.Framework.Fragment.Adapter;

import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

class ChampionViewHolder {
    private RoundedImageView championAvatar;
    private TextView textViewChampionName;

    TextView getTextViewChampionName() {
        return textViewChampionName;
    }

    ChampionViewHolder setTextViewChampionName(TextView textViewChampionName) {
        this.textViewChampionName = textViewChampionName;
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
