package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface FavoriteService {

    void addFavorite(int itemID);

    void deleteFavorite(int itemID);

    List<Item> getFavorite(int userID);

}
