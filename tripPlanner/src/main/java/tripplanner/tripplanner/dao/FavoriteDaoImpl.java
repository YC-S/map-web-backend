package tripplanner.tripplanner.dao;

import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.Item;

import java.util.List;

@Repository
public class FavoriteDaoImpl implements FavoriteDao {
    @Override
    public void addFavorite(int itemID) {

    }

    @Override
    public void deleteFavorite(int itemID) {

    }

    @Override
    public List<Item> getFavorite(int userID) {
        return null;
    }
}
