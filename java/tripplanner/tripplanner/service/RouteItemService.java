package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Item;

public interface RouteItemService {

    void addItem(int itemID);

    void removeItem(int itemID);

    void updateItem(int oldItemID, int newItemID);

    Item getItem(int itemID);

}
