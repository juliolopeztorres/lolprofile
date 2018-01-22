package oob.lolprofile.DetailsComponent.Data.Mapper;

import oob.lolprofile.HomeComponent.Data.Model.SkinRow;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Skin;

class SkinMapper {
    static Skin parseSkinRealm(SkinRow skinRow) {
        Skin skin = new Skin();

        skin
                .setId(skinRow.getId())
                .setName(skinRow.getName())
                .setNum(skinRow.getNum())
        ;

        return skin;
    }
}
