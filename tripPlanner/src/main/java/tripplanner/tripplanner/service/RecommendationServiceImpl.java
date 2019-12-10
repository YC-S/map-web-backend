package tripplanner.tripplanner.service;

import org.springframework.stereotype.Service;
import tripplanner.tripplanner.model.Item;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Override
    public List<Item> getRecommendation(int userID, double lat, double lon) {
        return null;
    }
}
