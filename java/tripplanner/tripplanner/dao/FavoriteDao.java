package tripplanner.tripplanner.dao;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface FavoriteDao {

    void addFavorite(int itemID);

    void deleteFavorite(int itemID);

    List<Item> getFavorite(int userID);

}
