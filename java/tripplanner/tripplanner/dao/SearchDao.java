package tripplanner.tripplanner.dao;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface SearchDao {

    List<Item> searchEvents(double lat, double lon, int numberOfItems);

}
