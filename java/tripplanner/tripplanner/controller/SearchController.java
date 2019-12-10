//package tripplanner.tripplanner.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import tripplanner.tripplanner.model.Item;
//import tripplanner.tripplanner.service.SearchServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class SearchController {
//
//
//    @Autowired
//    private SearchServiceImpl searchServiceImpl;
//
//    @GetMapping ("search")
//    public List<Item> search(@RequestParam ("lat") double lat, @RequestParam ("lon") double lon) {
//        List<Item> itemList = new ArrayList<>();
//        itemList.add(searchServiceImpl.searchEvent())；
//    }
//
//}
