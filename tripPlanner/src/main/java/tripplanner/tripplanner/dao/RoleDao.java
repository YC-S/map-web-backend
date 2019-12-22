package tripplanner.tripplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.Role;

@Repository("roleRepository")
public interface RoleDao extends JpaRepository<Role, Integer> {
  Role findByRole(String role);

}
