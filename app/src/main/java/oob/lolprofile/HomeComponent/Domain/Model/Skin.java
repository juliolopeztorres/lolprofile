package oob.lolprofile.HomeComponent.Domain.Model;

import java.io.Serializable;

public class Skin implements Serializable {
    private int id;
    private String name;
    private int num;

    public int getId() {
        return id;
    }

    public Skin setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Skin setName(String name) {
        this.name = name;
        return this;
    }

    public int getNum() {
        return num;
    }

    public Skin setNum(int num) {
        this.num = num;
        return this;
    }
}
