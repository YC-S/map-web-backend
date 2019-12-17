//package tripplanner.tripplanner.controller;
//
//import org.json.JSONException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import tripplanner.tripplanner.externalClient.SearchEventFromYelp;
//import tripplanner.tripplanner.model.Item;
//import tripplanner.tripplanner.service.ItemService;
//
//@RestController
//@RequestMapping("/api")
//public class ItemController {
//
//    @Autowired
//    private SearchEventFromYelp searchEventFromYelp;
//
//    @Autowired
//    private ItemService itemService;
//
//    //	add all events into DB
//    @PostMapping("/addAllItems")
//    public String addAllItems(@RequestParam("lat") double lat, @RequestParam("lon") double lon) throws JSONException {
//
//        // When limit < 0 or offest < 0, default value will be assigned;
//        // maxLimit = 50; maxAmountOfEventsPull = 1000;
//        // For test: lat = 47.6062, lon = -122.3321 (seattle);
//        Item[] itemArray = searchEventFromYelp.searchEvents(lat, lon, null, -1, -1);
//        if(itemArray != null) {
//        	itemService.addAllItems(itemArray);
//            return "Items are added successfully.";
//        }
//        return "Item are not added successfully or there are no more items in this area.";
//    }
//    
//    @PostMapping("/addItem")
//    public void addItem(@RequestBody Item item) {
//    	itemService.addItem(item);
//    }
//    
//    @GetMapping("/item/{itemId}")
//    public Item getItemById(@PathVariable String itemId) {
//    	Item curItem = itemService.getItemById(itemId);
//		if(curItem == null) {
//			throw new RuntimeException("ItemId to be get cannot be found - " + itemId);
//		}
//		return curItem;
//    }
//    
//    @DeleteMapping("/item/{itemId}")
//    public void deleteItemById(@PathVariable String itemId) {
//    	Item curItem = itemService.getItemById(itemId);
//    	if(curItem == null) {
//			throw new RuntimeException("ItemId to be deleted cannot be found - " + itemId);
//		}
//    	itemService.deleteItemById(itemId);
//    }
//}

package tripplanner.tripplanner.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tripplanner.tripplanner.externalClient.SearchEventFromYelp;
import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private SearchEventFromYelp searchEventFromYelp;

    @Autowired
    private ItemService itemService;

    //	add all events into DB
    @PostMapping("/addAllItems")
    public String addAllItems(@RequestParam("lat") double lat, @RequestParam("lon") double lon) throws JSONException {

        // When limit < 0 or offest < 0, default value will be assigned;
        // maxLimit = 50; maxAmountOfEventsPull = 1000;
        // For test: lat = 47.6062, lon = -122.3321 (seattle);
        Item[] itemArray = searchEventFromYelp.searchEvents(lat, lon, null, -1, -1);
        
        try {
        	itemService.addAllItems(itemArray);
        	return "Items are added successfully.";
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "Item are not added successfully or there are no more items in this area.";
    }
    
    @PostMapping("/addItem")
    public void addItem(Item item) {
    	itemService.addItem(item);
    }
    
    @PutMapping("/addItem")
    public void updateItem(Item item) {
    	itemService.addItem(item);
    }
    
    @GetMapping("/items/{itemId}")
    public Item getItemById(@PathVariable String itemId) {
    	Item theItem = itemService.getItemById(itemId);
    	return theItem;
    }
    
    @DeleteMapping("/items/{itemId}")
    public String deleteItemById(@PathVariable String itemId) {
    	Item foundItem= itemService.getItemById(itemId);
		if(foundItem == null) {
			throw new RuntimeException("Item id not found - " + itemId);
		}
		itemService.deleteItemById(itemId);	
		return "Deleted item id - " + itemId;
    }  
}
