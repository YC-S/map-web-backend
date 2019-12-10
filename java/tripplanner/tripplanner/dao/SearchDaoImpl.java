package tripplanner.tripplanner.dao;

import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.Item;

import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {
    @Override
    public List<Item> searchEvents(double lat, double lon, int numberOfItems) {
        return null;
    }
}
