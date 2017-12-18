package oob.lolprofile.HomeComponent.Data.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class ChampionRow extends RealmObject {

    private int id;
    @Required
    private String name;
    @Required
    private String title;
    @Required
    private String lore;
    @Required
    private String image;
    @Required
    private RealmList<SkinRow> skins;

    public int getId() {
        return id;
    }

    public ChampionRow setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChampionRow setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ChampionRow setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLore() {
        return lore;
    }

    public ChampionRow setLore(String lore) {
        this.lore = lore;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ChampionRow setImage(String image) {
        this.image = image;
        return this;
    }

    public RealmList<SkinRow> getSkins() {
        return skins;
    }

    public ChampionRow setSkins(RealmList<SkinRow> skins) {
        this.skins = skins;
        return this;
    }
}
