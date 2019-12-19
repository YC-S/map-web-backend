package tripplanner.tripplanner.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.Profile;
import tripplanner.tripplanner.model.User;
// import tripplanner.tripplanner.service.HibernateSearchService;
import tripplanner.tripplanner.service.ProfileService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  @Autowired
  private UserDao userDao;
  
  @Autowired
  private ProfileService profileService;

  //  @Autowired private HibernateSearchService searchService;

  //  @Autowired private UserService userService;

  @PostMapping("/users/login")
  public ResponseEntity<?> validateLogin(@RequestBody User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PostMapping("/users/logout")
  public ResponseEntity<?> logout(HttpSession session) {
    session.invalidate();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/users/register")
  public String registerUser(@RequestBody User user) {
    userDao.save(user);
    Profile profile = new Profile();
    String profileId = profileService.addOrUpdateProfile(profile);
    // return profileId, so frontend can redirect to profile page using profileId
    return "corresponding profile id is " + profileId;
  }
  
//@RequestMapping(value = "/users/search", method = RequestMethod.GET)
//  public String search(@RequestBody User user) {
//    List<User> searchResults = null;
//    try {
////      userService.addUser(user);
//      searchResults = searchService.fuzzySearch(user.getUserName());
//    } catch (Exception ex) {
//      // here you should handle unexpected errors
//      // ...
//      // throw ex;
//    }
//    return searchResults.toString();
//  }
}
