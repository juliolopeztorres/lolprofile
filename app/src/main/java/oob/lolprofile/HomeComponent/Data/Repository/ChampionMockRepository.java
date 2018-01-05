package oob.lolprofile.HomeComponent.Data.Repository;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Random;

import oob.lolprofile.HomeComponent.Data.Mapper.ChampionCollectionMapper;
import oob.lolprofile.HomeComponent.Data.NoChampionsFoundDBException;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Champion;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.ChampionRepositoryInterface;
import oob.lolprofile.HomeComponent.Domain.GetAllChampions.Model.Skin;

public class ChampionMockRepository implements ChampionRepositoryInterface {

    private ChampionDBRepository championDBRepository;
    private SharedPreferences sharedPreferences;
    private String keySecondsLastRequest;

    public ChampionMockRepository(ChampionDBRepository championDBRepository, SharedPreferences sharedPreferences, String keySecondsLastRequest) {
        this.championDBRepository = championDBRepository;
        this.sharedPreferences = sharedPreferences;
        this.keySecondsLastRequest = keySecondsLastRequest;
    }

    @Override
    public void getAll(ChampionCallback callback) {
        ArrayList<Champion> champions;
        try {
            champions = ChampionCollectionMapper.parseChampionsRealmResponse(
                    this.championDBRepository.getAll()
            );
        } catch (NoChampionsFoundDBException e) {
            champions = this.getChampions();
            this.championDBRepository.setAll(
                    ChampionCollectionMapper.parseChampionsToRealm(champions)
            );
        }

        callback.onSuccess(champions);
    }

    @Override
    public void removeAll() {
        this.championDBRepository.removeAll();
    }

    @Override
    public int getSecondsLastRequest() {
        return this.sharedPreferences.getInt(this.keySecondsLastRequest, 0);
    }

    @Override
    public void setSecondsLastRequest(int secondsCurrent) {
        this.sharedPreferences.edit().putInt(this.keySecondsLastRequest, secondsCurrent).apply();
    }

    private Champion getChampion() {
        final Skin skinTable = new Skin();
        final Skin skinTable2 = new Skin();

        skinTable.setId(1).setName("skin").setNum(001);
        skinTable2.setId(2).setName("skin 2").setNum(002);

        return new Champion().setId(new Random().nextInt()).setImage("Yasuo.png").setLore("Lore").setName("Yasuo").setTitle("Title")
                .setSkins(new ArrayList<Skin>(){{
                    add(skinTable);
                    add(skinTable2);
                }});
    }

    private ArrayList<Champion> getChampions() {
        return new ArrayList<Champion>() {{
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
            add(getChampion());
        }};
    }
}
