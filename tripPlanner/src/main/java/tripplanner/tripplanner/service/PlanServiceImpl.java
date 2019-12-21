package tripplanner.tripplanner.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tripplanner.tripplanner.dao.PlanDao;
import tripplanner.tripplanner.model.Plan;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanDao planDao;

	@Override
	public void deletePlanById(String planId) {
		planDao.deleteById(planId);
	}

	@Override
	public Plan getPlanById(String planId) {
		Optional<Plan> result = planDao.findById(planId);
		Plan thePlan = null;
		if(result.isPresent()) {
			thePlan = result.get();
		} else {
			throw new RuntimeException("Did not find plan id - " + planId);
		}
		return thePlan;
	}

	@Override
	public void createNewPlan(Plan plan) {
		planDao.saveAndFlush(plan);
	}

}
