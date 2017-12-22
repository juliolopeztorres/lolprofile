package oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model;

import android.support.annotation.NonNull;
import java.util.ArrayList;

public class Counter implements Comparable {
    private int id;
    private double winRate;
    private int wins;

    public int getId() {
        return id;
    }

    public Counter setId(int id) {
        this.id = id;
        return this;
    }

    public double getWinRate() {
        return winRate;
    }

    public Counter setWinRate(double winRate) {
        this.winRate = winRate;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public Counter setWins(int wins) {
        this.wins = wins;
        return this;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return (((Counter) o).winRate - this.winRate > 0 ? 1 : -1);
    }

    public static ArrayList<Counter> filter(ArrayList<Counter> counters, int beginning, int ending) {
        if (counters.size() > (ending - beginning)) {
            counters = new ArrayList<>(counters.subList(beginning, ending));
        }

        return counters;
    }

    public static ArrayList<Counter> getCountersFilteredById(ArrayList<Counter> counters, int champId) {
        ArrayList<Counter> countersFiltered = new ArrayList<>();
        for (Counter counter: counters) {
            if (counter.getId() != champId) {
                countersFiltered.add(counter);
            }
        }

        return countersFiltered;
    }
}
