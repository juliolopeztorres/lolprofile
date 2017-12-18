package oob.lolprofile.HomeComponent.Framework.Adapter;

import com.makeramen.roundedimageview.RoundedImageView;

class ChampionViewHolder {
    private RoundedImageView championAvatar;

    RoundedImageView getChampionAvatar() {
        return championAvatar;
    }

    ChampionViewHolder setChampionAvatar(RoundedImageView championAvatar) {
        this.championAvatar = championAvatar;
        return this;
    }
}
