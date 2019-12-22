package tripplanner.tripplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tripplanner.tripplanner.dao.ItemDao;
import tripplanner.tripplanner.model.Item;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public void addAllItems(Item[] itemArray) {
		for (Item item : itemArray) {
            itemDao.save(item);
        }
	}
	
	@Override
	public List<Item> findAllItems() {
		return (List<Item>) itemDao.findAll();
	}

	@Override
	public void addItem(Item item) {
		itemDao.save(item);
	}

	@Override
	public void deleteItemById(String itemId) {
		itemDao.deleteById(itemId);
	}

	@Override
	public Item getItemById(String itemId) {
		Optional<Item> result = itemDao.findById(itemId);
		Item theItem = null;
		if(result.isPresent()) {
			theItem = result.get();
		} else {
			throw new RuntimeException("Did not find item id - " + itemId);
		}
		return theItem;
	}   
}
