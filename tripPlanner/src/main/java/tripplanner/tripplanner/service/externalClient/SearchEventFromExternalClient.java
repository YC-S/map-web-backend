package tripplanner.tripplanner.service.externalClient;

import org.json.JSONException;
import tripplanner.tripplanner.model.Item;

public interface SearchEventFromExternalClient {

    Item[] searchEvents (double lat, double lon, String category, int limit, int offset) throws JSONException;

}
