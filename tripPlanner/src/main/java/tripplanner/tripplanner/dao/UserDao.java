package tripplanner.tripplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.User;

@Repository("userRepository")
public interface UserDao extends JpaRepository<User, String> {
  User findByEmail(String email);
  User findByUsername(String username);
}
