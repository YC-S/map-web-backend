package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface RecommendationService {

    //Recommendation based on selected geolocation and user's favorite

    List<Item> getRecommendation(int userID, double lat, double lon);
}
