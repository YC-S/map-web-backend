package tripplanner.tripplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {


}
