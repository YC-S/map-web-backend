package tripplanner.tripplanner.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tripplanner.tripplanner.dao.PlanDao;
import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.model.Plan;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.ItemService;
import tripplanner.tripplanner.service.PlanService;
import tripplanner.tripplanner.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PlanDao planDao;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;
	
	final static Logger logger = LoggerFactory.getLogger(PlanController.class);
	
		@PostMapping("/addItem/{planId}/{itemId}")
		public void addItemToPlan(@PathVariable("planId") String planId, @PathVariable("itemId") String itemId) {
			Plan curPlan = planService.getPlanById(planId);
			String curPlanItems = curPlan.getPlanItems();
			Set<String> set = new HashSet<>();
			if(curPlanItems != null) {
				String[] items = curPlanItems.split(",");
				for(String item : items) {
					set.add(item);
				}
			}
			logger.info("item id = " + itemId);
			
			logger.info("curPlanItems = " + curPlanItems);
			if(curPlanItems == null) {
				curPlanItems = "" + itemId;
			} else if(!set.contains(itemId)) {
				curPlanItems = curPlanItems + "," + itemId;
			} else {
				throw new RuntimeException("This item is already in this plan - " + itemId);
			}
			
			logger.info("curPlanItems after = " + curPlanItems);
			curPlan.setPlanItems(curPlanItems);
			planDao.save(curPlan);
		}
		
		@DeleteMapping("/deleteItem/{planId}/{itemId}")
		public void deleteItemFromPlan(@PathVariable("planId") String planId, @PathVariable("itemId") String itemId) {
			Plan curPlan = planService.getPlanById(planId);
			String curPlanItems = curPlan.getPlanItems();
			if(curPlanItems == null) {
				throw new RuntimeException("There is no item in this Plan - " + planId);
			}
			String[] itemIds = curPlanItems.split(",");
			
			// find the index of the to-be-deleted itemId
			int deletedItemIndex = -1;
			for(int i = 0; i < itemIds.length; i++) {
				if(itemIds[i].equals(itemId)) {
					deletedItemIndex = i;
					break;
				}
			}
			
			if(deletedItemIndex == -1) {
				throw new RuntimeException("This item is not existing in this Plan - " + itemId);
			}
			
			logger.info("deletedItemIndex = " + deletedItemIndex);
			
			// delete the itemId on certain index
			String[] deletedPlanItems = new String[itemIds.length - 1];
			for(int i = 0, k = 0; i < itemIds.length; i++) {
				if(i == deletedItemIndex) {
					continue;
				}
				deletedPlanItems[k++] = itemIds[i];
			}
			
			// change a String of itemIds into one String
			String newItemIds = "";
			if(deletedPlanItems.length == 0) {
				curPlan.setPlanItems(null);
			} else {
				for(String itemid : deletedPlanItems) {
					if(newItemIds.length() == 0) {
						newItemIds = itemid;
					} else {
						newItemIds = newItemIds +  "," + itemid;
					}
					logger.info("newItemIds = " + newItemIds);
				}
			}
			
			curPlan.setPlanItems(newItemIds);
			planDao.save(curPlan);
		}
		
		@GetMapping("/planItems/{planId}")
		public List<Item> getPlanItems(@PathVariable String planId) {
			Plan curPlan = planService.getPlanById(planId);
			logger.info("curPlan is " + curPlan.getId());
			String[] itemIds = curPlan.getPlanItems().split(",");
			List<Item> items = new ArrayList<>();
			for(String itemId : itemIds) {
				Item curItem = itemService.getItemById(itemId);
				logger.info("current item id = " + curItem.getId());
				if (curItem != null) {
					items.add(curItem);
				}
			}
			return items;
		}
	
	 	@PostMapping("/addPlan/{userId}")
	    public Plan addPlan(@RequestBody Plan plan, @PathVariable String userId) {
	 		User curUser = userService.findUserById(userId);
	 		curUser.add(plan);
		 	return planService.createNewPlan(plan);
	    }
	    
	    @PutMapping("/addPlan")
	    public void updateItem(@RequestBody Plan plan) {
	    	planService.createNewPlan(plan);
	    }
	    
	    @GetMapping("/plans/{planId}")
	    public Plan getPlanById(@PathVariable String planId) {
	    	Plan thePlan = planService.getPlanById(planId);
	    	return thePlan;
	    }
	    
	    
	    @DeleteMapping("/plans/{planId}")
	    public String deletePlanById(@PathVariable String planId) {
	    	Plan foundPlan= planService.getPlanById(planId);
			if(foundPlan == null) {
				throw new RuntimeException("Plan id not found - " + planId);
			}
			planService.deletePlanById(planId);	
			return "Deleted plan id - " + planId;
	    }  
}

