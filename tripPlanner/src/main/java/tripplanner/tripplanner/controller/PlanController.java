package tripplanner.tripplanner.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.model.Plan;
import tripplanner.tripplanner.service.PlanService;

@RestController
@RequestMapping("/api")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
		@PostMapping("/addItem/{planId}")
		public String addItemToPlan(@PathVariable String planId, Item item) {
			Plan curPlan = planService.getPlanById(planId);
			Set<Item> curPlanItems = curPlan.getPlanItems();
			
			if(curPlanItems == null) {
				curPlanItems = new HashSet<>();
			}
			curPlanItems.add(item);	
			curPlan.setPlanItems(curPlanItems);
			return "Item has been added to plan.";
		}
	
	 	@PostMapping("/addPlan")
	    public void addPlan(Plan plan) {
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

