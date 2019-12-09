package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.User;

public interface UserService {

    void addUser(User user);

    void deleteUser(int userID);

    void updateUser(int userID, User user);

    User getUserByUserName(int userID);

}
