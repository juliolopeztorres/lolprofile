package oob.lolprofile.DetailsComponent.Domain.CounterChampions.Model;

import java.util.ArrayList;
import oob.lolprofile.Util.RoleNamesParser;

public class ChampionRoleCounter {
    private int id;
    private String role;
    private String elo;
    private double winRate;
    private double kills;
    private double assists;
    private double deaths;
    private double playRate;
    private int gamesPlayed;
    private double percentRolePlayed;
    private double banRate;
    private ArrayList<RoleCounter> roleCounters;

    public int getId() {
        return id;
    }

    public ChampionRoleCounter setId(int id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public ChampionRoleCounter setRole(String role) {
        this.role = role;
        return this;
    }

    public String getElo() {
        return elo;
    }

    public ChampionRoleCounter setElo(String elo) {
        this.elo = elo;
        return this;
    }

    public double getWinRate() {
        return winRate;
    }

    public ChampionRoleCounter setWinRate(double winRate) {
        this.winRate = winRate;
        return this;
    }

    public double getKills() {
        return kills;
    }

    public ChampionRoleCounter setKills(double kills) {
        this.kills = kills;
        return this;
    }

    public double getAssists() {
        return assists;
    }

    public ChampionRoleCounter setAssists(double assists) {
        this.assists = assists;
        return this;
    }

    public double getDeaths() {
        return deaths;
    }

    public ChampionRoleCounter setDeaths(double deaths) {
        this.deaths = deaths;
        return this;
    }

    public double getPlayRate() {
        return playRate;
    }

    public ChampionRoleCounter setPlayRate(double playRate) {
        this.playRate = playRate;
        return this;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public ChampionRoleCounter setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
        return this;
    }

    public double getPercentRolePlayed() {
        return percentRolePlayed;
    }

    public ChampionRoleCounter setPercentRolePlayed(double percentRolePlayed) {
        this.percentRolePlayed = percentRolePlayed;
        return this;
    }

    public double getBanRate() {
        return banRate;
    }

    public ChampionRoleCounter setBanRate(double banRate) {
        this.banRate = banRate;
        return this;
    }

    public ArrayList<RoleCounter> getRoleCounters() {
        return roleCounters;
    }

    public ChampionRoleCounter setRoleCounters(ArrayList<RoleCounter> roleCounters) {
        this.roleCounters = roleCounters;
        return this;
    }

    public static ArrayList<String> getChampRoles(ArrayList<ChampionRoleCounter> championRoleCounters) {
        ArrayList<String> roles = new ArrayList<>();
        for(ChampionRoleCounter championRoleCounter: championRoleCounters) {
            if (hasCounterInformation(championRoleCounter.getRoleCounters())) {
                roles.add(RoleNamesParser.parse(championRoleCounter.getRole()));
            }
        }
        return roles;
    }

    private static boolean hasCounterInformation(ArrayList<RoleCounter> roleCounters) {
        for (RoleCounter roleCounter: roleCounters) {
            if (!roleCounter.getCounters().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Counter> getCountersByRole(ArrayList<ChampionRoleCounter> championRoleCounters, String role, int championId) {
        for(ChampionRoleCounter championRoleCounter: championRoleCounters) {
            if (championRoleCounter.getRole().equals(role)) {
                return RoleCounter.getCountersBySameRole(championRoleCounter.getRoleCounters(), role, championId);
            }
        }

        return null;
    }

    public static ChampionRoleCounter getStatsByRole(ArrayList<ChampionRoleCounter> championRoleCounters, String role) {
        for(ChampionRoleCounter championRoleCounter: championRoleCounters) {
            if (championRoleCounter.getRole().equals(role)) {
                return championRoleCounter;
            }
        }

        return null;
    }
}
