package oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model;

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

    public static ArrayList<Counter> getCountersBySameRole(ArrayList<RoleCounter> roleCounters, String role, int champId) {
        for (RoleCounter roleCounter: roleCounters) {
            if (roleCounter.getRole().equals(role)) {
                return Counter.getCountersFilteredById(roleCounter.getCounters(), champId);
            }
        }

        return null;
    }
}
