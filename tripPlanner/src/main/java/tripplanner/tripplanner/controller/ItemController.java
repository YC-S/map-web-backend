package tripplanner.tripplanner.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.dao.ItemDao;
import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.service.externalClient.SearchDescriptionFromWiki;
import tripplanner.tripplanner.service.externalClient.SearchEventFromYelp;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

  @Autowired private SearchEventFromYelp searchEventFromYelp;

  @Autowired private ItemDao itemDao;

  @Autowired private SearchDescriptionFromWiki searchDescriptionFromWiki;

  //	add all events into DB
  @PostMapping("addAllItems")
  public String addAllItems(@RequestParam("lat") double lat, @RequestParam("lon") double lon)
      throws JSONException {

    // When limit < 0 or offest < 0, default value will be assigned;
    // maxLimit = 50; maxAmountOfEventsPull = 1000;
    // For test: lat = 47.6062, lon = -122.3321 (seattle);
    Item[] itemArray = searchEventFromYelp.searchEvents(lat, lon, null, -1, -1);

    for (Item item : itemArray) {
      System.out.println(item.toString());
      searchDescriptionFromWiki.searchDescription(item);
      itemDao.save(item);
    }

    if (itemArray != null) {
      return "Items are added successfully.";
    }

    return "Item are not added successfully or there are no more items in this area.";
  }
}
