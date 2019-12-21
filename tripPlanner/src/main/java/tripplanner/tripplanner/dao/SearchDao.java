package tripplanner.tripplanner.dao;

import org.springframework.data.repository.CrudRepository;
import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface SearchDao {

    // for landing
    List<Item> searchEvents();
    // for keyword search
    List<Item> searchEvents(String keyword);

}
