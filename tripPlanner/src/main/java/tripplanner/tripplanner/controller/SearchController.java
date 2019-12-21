package tripplanner.tripplanner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.service.SearchServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchServiceImpl searchServiceImpl;

    @GetMapping ("landing")
    public List<Item> search() {
        return searchServiceImpl.searchEvents();
    }

    @GetMapping ("search")
    public List<Item> search(@RequestParam ("keyword") String keyword) {
        return searchServiceImpl.searchEvents(keyword);
    }

}
