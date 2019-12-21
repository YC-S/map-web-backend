package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface SearchService {
    // for landing
    List<Item> searchEvents();
    // for keyword search
    List<Item> searchEvents(String keyword);

}
