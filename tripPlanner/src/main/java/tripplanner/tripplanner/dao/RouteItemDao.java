package tripplanner.tripplanner.dao;

import tripplanner.tripplanner.model.Item;

public interface RouteItemDao {

    void addItem(int itemID);

    void removeItem(int itemID);

    void updateItem(int oldItemID, int newItemID);

    Item getItem(int itemID);

}
