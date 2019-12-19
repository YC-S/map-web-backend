package tripplanner.tripplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tripplanner.tripplanner.model.Plan;

public interface PlanDao extends JpaRepository<Plan, String> {}
