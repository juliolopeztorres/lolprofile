package oob.lolprofile.DetailsComponent.Domain.Model;

import java.util.ArrayList;

public class RoleCounter {
    private String role;
    private ArrayList<Counter> counters;

    public String getRole() {
        return role;
    }

    public RoleCounter setRole(String role) {
        this.role = role;
        return this;
    }

    public ArrayList<Counter> getCounters() {
        return counters;
    }

    public RoleCounter setCounters(ArrayList<Counter> counters) {
        this.counters = counters;
        return this;
    }
}
