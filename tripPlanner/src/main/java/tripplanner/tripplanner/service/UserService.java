package tripplanner.tripplanner.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tripplanner.tripplanner.dao.RoleDao;
import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.model.Role;
import tripplanner.tripplanner.model.User;

@Service("userService")
public class UserService {

  private UserDao userDao;
  private RoleDao roleDao;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserService(
      UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userDao = userDao;
    this.roleDao = roleDao;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public User findUserByEmail(String email) {
    return userDao.findByEmail(email);
  }

  public User findUserByUsername(String username) {
    return userDao.findByUsername(username);
  }

  public User saveUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    Role userRole = roleDao.findByRole("ADMIN");
    return userDao.save(user);
  }
  
  public User findUserById(String userId) {
	  Optional<User> result = userDao.findById(userId);
		User theUser = null;
		if(result.isPresent()) {
			theUser = result.get();
		} else {
			throw new RuntimeException("Did not find user id - " + userId);
		}
		return theUser;
  }
}
