package oob.lolprofile.HomeComponent.Domain.DeleteStoredData;


public class DeleteStoredDataUseCase {
    private ChampionDBInterface championDB;

    public DeleteStoredDataUseCase(ChampionDBInterface championDB) {
        this.championDB = championDB;
    }

    public void deleteStoredData() {
        this.championDB.removeAll();
    }
}
