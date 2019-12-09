package tripplanner.tripplanner.dao;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface RecommendationDao {

    List<Item> getRecommendation(int userID, double lat, double lon);
}
