package oob.lolprofile.DetailsComponent.Data.Repository;

import oob.lolprofile.DetailsComponent.Data.Mapper.ChampionCollectionMapper;
import oob.lolprofile.DetailsComponent.Data.NoChampionsFoundDBException;
import oob.lolprofile.DetailsComponent.Domain.GetAllChampions.ChampionRepositoryInterface;

public class ChampionRepository implements ChampionRepositoryInterface {
    private ChampionDBRepository championDBRepository;

    public ChampionRepository(ChampionDBRepository championDBRepository) {
        this.championDBRepository = championDBRepository;
    }

    @Override
    public void getAll(final ChampionCallback callback) {
        try {
            callback.onSuccess(
                    ChampionCollectionMapper.parseChampionsRealmResponse(
                            this.championDBRepository.getAll()
                    )
            );
        } catch (NoChampionsFoundDBException e) {
            callback.onError(e.getMessage());
        }
    }
}
