package tripplanner.tripplanner.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.objects.NativeJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.Plan;
import tripplanner.tripplanner.model.Profile;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.ProfileService;
import tripplanner.tripplanner.service.UserService;

@RestController
@RequestMapping("/api")
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
  public String validateLogin(@RequestBody User user) {
    User userInRepo = userService.findUserByUsername(user.getUsername());
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    if (encoder.matches(user.getPassword(), userInRepo.getPassword())) {
      return userInRepo.getCores_profile().getId();
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
  public String registerUser(@RequestBody User user) throws Exception {
    User userExists = userService.findUserByEmail(user.getEmail());
    if (userExists != null) {
      throw new Exception("there is already a user exist");
    } else {
      Profile profile = new Profile();
      user.setCores_profile(profile);
      userService.saveUser(user);
      //String profileId = profileService.addOrUpdateProfile(profile);
      // return profileId, so frontend can redirect to profile page using profileId
      return user.getCores_profile().getId();
    }
  }
  
  @GetMapping("/getPlans/{userId}")
  public List<Plan> getPlans(@PathVariable String userId) {
	  return userService.findUserById(userId).getPlans();
  }
}
