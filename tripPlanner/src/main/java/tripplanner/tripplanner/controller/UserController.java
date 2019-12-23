package tripplanner.tripplanner.controller;

import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.objects.NativeJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.Profile;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.ProfileService;
import tripplanner.tripplanner.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserService userService;
  
  @Autowired
  private ProfileService profileService;

  //  @Autowired private HibernateSearchService searchService;

  //  @Autowired private UserService userService;

  @PostMapping("/users/login")
  public User validateLogin(@RequestBody User user) {
    User userInRepo = userService.findUserByUsername(user.getUsername());
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    if (encoder.matches(user.getPassword(), userInRepo.getPassword())) {
      return userInRepo;
    } else {
      return null;
    }
  }

  @PostMapping("/users/logout")
  public ResponseEntity<?> logout(HttpSession session) {
    session.invalidate();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/users/register")
  public ResponseEntity<?>  registerUser(@RequestBody User user) {
    User userExists = userService.findUserByEmail(user.getEmail());
    if (userExists != null) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      userService.saveUser(user);
      Profile profile = new Profile();
      String profileId = profileService.addOrUpdateProfile(profile);
      // return profileId, so frontend can redirect to profile page using profileId
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }
}
