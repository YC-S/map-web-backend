package tripplanner.tripplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.User;

@Service
public class UserServiceImpl implements UserService {
  @Override
  public void addUser(User user) {
    test1.setUserName("Ted Will");
    test1.setId(1);
    test1.setEmail("tedwill@gmail.com");
    test1.setPassword("123");
    userDao.save(test1);

    test1.setUserName("Bob Gibson");
    test1.setId(2);
    test1.setEmail("bobgibson@gmail.com");
    test1.setPassword("123");
    userDao.save(test2);

    test1.setUserName("Hognus Wagner");
    test1.setId(3);
    test1.setEmail("honuswagner@gmail.com");
    test1.setPassword("123");
    userDao.save(test3);
  }

  @Override
  public void addUser() {};

  @Override
  public void deleteUser(int userID) {}

  @Override
  public void updateUser(int userID, User user) {}

  @Override
  public User getUserByUserName(int userID) {
    return null;
  }

  @Autowired
  UserDao userDao;

  User test1 = new User();
  User test2 = new User();
  User test3 = new User();

//  @Override
//  public boolean findUser(User user) {
//    if (user == null) {
//      return false;
//    }
//    return new org.springframework.security.core.userdetails.User(
//      user.getUserName(),
//      user.getPassword(),
//      auth
//    );
//
//  }
}
