package tripplanner.tripplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tripplanner.tripplanner.model.User;

public interface UserDao extends JpaRepository<User, String>{}
