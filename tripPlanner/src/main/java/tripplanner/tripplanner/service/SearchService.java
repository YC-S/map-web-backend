package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface SearchService {

    //	search events from a given geolocation (probably type of event)
    Item searchEvent(double lat, double lon);

}
