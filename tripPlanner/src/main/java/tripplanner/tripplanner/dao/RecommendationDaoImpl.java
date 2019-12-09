package tripplanner.tripplanner.dao;

import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.Item;

import java.util.List;

@Repository
public class RecommendationDaoImpl implements RecommendationDao {
    @Override
    public List<Item> getRecommendation(int userID, double lat, double lon) {
        return null;
    }
}
