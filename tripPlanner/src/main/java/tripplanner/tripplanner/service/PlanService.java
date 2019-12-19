package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Plan;

public interface PlanService {
	
//	public List<Plan> findAllPlansOfCurrentUser(int userId);
//  public void addItemToPlan(Item item);

    public void createNewPlan(Plan plan);
    
    public void deletePlanById(String planId);

    public Plan getPlanById(String planId);

}