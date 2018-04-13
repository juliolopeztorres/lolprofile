package oob.lolprofile.HomeComponent.Data.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class ChampionRow extends RealmObject {

    private int id;
    @Required
    private String name;
    @Required
    private String key;
    @Required
    private String title;
    @Required
    private String lore;
    @Required
    private String image;
    @Required
    private RealmList<SkinRow> skins;
    @Required
    private RealmList<String> allyTips;
    @Required
    private RealmList<String> enemyTips;

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

    public String getKey() {
        return key;
    }

    public ChampionRow setKey(String key) {
        this.key = key;
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

    public RealmList<String> getAllyTips() {
        return allyTips;
    }

    public ChampionRow setAllyTips(RealmList<String> allyTips) {
        this.allyTips = allyTips;
        return this;
    }

    public RealmList<String> getEnemyTips() {
        return enemyTips;
    }

    public ChampionRow setEnemyTips(RealmList<String> enemyTips) {
        this.enemyTips = enemyTips;
        return this;
    }
}
