package tripplanner.tripplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tripplanner.tripplanner.dao.SearchDaoImpl;
import tripplanner.tripplanner.model.Item;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDaoImpl searchDaoImpl;

    // for landing
    @Override
    public List<Item> searchEvents() { return searchDaoImpl.searchEvents(); }

    // for keyword search
    @Override
    public List<Item> searchEvents(String keyword) {
        return searchDaoImpl.searchEvents(keyword);
    }
}
