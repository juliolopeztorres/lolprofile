package oob.lolprofile.HomeComponent.Data.Model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class SkinRow extends RealmObject {

    private int id;
    @Required
    private String name;
    private int num;

    public int getId() {
        return id;
    }

    public SkinRow setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SkinRow setName(String name) {
        this.name = name;
        return this;
    }

    public int getNum() {
        return num;
    }

    public SkinRow setNum(int num) {
        this.num = num;
        return this;
    }
}
