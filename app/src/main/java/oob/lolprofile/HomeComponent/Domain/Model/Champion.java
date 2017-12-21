package oob.lolprofile.HomeComponent.Domain.Model;

import java.util.ArrayList;

public class Champion {
    private int id;
    private String name;
    private String title;
    private String lore;
    private String image;
    private ArrayList<Skin> skins;

    public int getId() {
        return id;
    }

    public Champion setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Champion setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Champion setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLore() {
        return lore;
    }

    public Champion setLore(String lore) {
        this.lore = lore;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Champion setImage(String image) {
        this.image = image;
        return this;
    }

    public ArrayList<Skin> getSkins() {
        return skins;
    }

    public Champion setSkins(ArrayList<Skin> skins) {
        this.skins = skins;
        return this;
    }

    public static Champion findById(ArrayList<Champion> champions, int id) {
        for(Champion champion: champions) {
            if (id == champion.getId()) {
                return champion;
            }
        }

        return null;
    }
}
