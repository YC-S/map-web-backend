package tripplanner.tripplanner.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		public Plan addItemToPlan(@PathVariable("planId") String planId, @PathVariable("itemId") String itemId) {
			Plan curPlan = planService.getPlanById(planId);
			Item thisItem = itemService.getItemById(itemId);
			curPlan.add(thisItem);
			return planService.savePlan(curPlan);
		}
		
		@DeleteMapping("/deleteItem/{planId}/{itemId}")
		public Plan deleteItemFromPlan(@PathVariable("planId") String planId, @PathVariable("itemId") String itemId) {
			Plan curPlan = planService.getPlanById(planId);
			List<Item> curPlanItems = curPlan.getPlanItems();
			if(curPlanItems == null) {
				throw new RuntimeException("There is no item in this Plan - " + planId);
			}
			Item deletedItem = itemService.getItemById(itemId);
			curPlanItems.remove(deletedItem);
			curPlan.setPlanItems(curPlanItems);
			return planDao.save(curPlan);
		}
		
		@GetMapping("/planItems/{planId}")
		public List<Item> getPlanItems(@PathVariable String planId) {
			return planService.getPlanById(planId).getPlanItems();
		}
	
	 	@PostMapping("/addPlan/{userId}")
	    public Plan addPlan(@RequestBody Plan plan, @PathVariable String userId) {
	 		User curUser = userService.findUserById(userId);
	 		curUser.add(plan);
		 	return planService.savePlan(plan);
	    }
	    
	    @PutMapping("/addPlan")
	    public void updatePlan(@RequestBody Plan plan) {
	    	planService.savePlan(plan);
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

