package tripplanner.tripplanner.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import tripplanner.tripplanner.service.ItemService;
import tripplanner.tripplanner.service.PlanService;

@RestController
@RequestMapping("/api")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PlanDao planDao;
	
	@Autowired
	private ItemService itemService;
	
	final static Logger logger = LoggerFactory.getLogger(PlanController.class);
	
		@PostMapping("/addItem/{planId}/{itemId}")
		public void addItemToPlan(@PathVariable("planId") String planId, @PathVariable("itemId") String itemId) {
			Plan curPlan = planService.getPlanById(planId);
			String curPlanItems = curPlan.getPlanItems();
			logger.info("item id = " + itemId);
			
			logger.info("curPlanItems = " + curPlanItems);
			if(curPlanItems == null) {
				curPlanItems = "" + itemId;
			} else {
				curPlanItems = curPlanItems + "," + itemId;
			}
			
			logger.info("curPlanItems after = " + curPlanItems);
			curPlan.setPlanItems(curPlanItems);
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
	
	 	@PostMapping("/addPlan")
	    public void addPlan(@RequestBody Plan plan) {
		 	planService.createNewPlan(plan);
	    }
	    
	    @PutMapping("/addPlan")
	    public void updateItem(Plan plan) {
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

