package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Item;

import java.util.List;

public interface ItemService {

	public void addAllItems(Item[] itemArray);
	
	public List<Item> findAllItems();

    public void addItem(Item item);
    
    public void deleteItemById(String itemId);

    public Item getItemById(String itemId);

}
