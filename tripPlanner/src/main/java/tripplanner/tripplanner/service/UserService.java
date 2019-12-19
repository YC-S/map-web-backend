package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.User;

public interface UserService {

    void addUser(User user);

    void deleteUser(String userID);

    void updateUser(User user);

    User getUserByUserId(String userId);

}
