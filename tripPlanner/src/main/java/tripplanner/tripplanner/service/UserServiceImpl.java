package tripplanner.tripplanner.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public void deleteUser(String userId) {
		userDao.deleteById(userId);
	}

	@Override
	public void updateUser(User user) {
		userDao.save(user);
	}

	@Override
	public User getUserByUserId(String userId) {
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
