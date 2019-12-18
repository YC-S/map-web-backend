package tripplanner.tripplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.User;
// import tripplanner.tripplanner.service.HibernateSearchService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  @Autowired
  private UserDao userDao;

  //  @Autowired private HibernateSearchService searchService;

  //  @Autowired private UserService userService;

//  @PostMapping("/users/login")
//  public ResponseEntity<?> validateLogin(@RequestBody User user, BindingResult bindingResult) {
//    if (bindingResult.hasErrors()) {
//      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    } else {
//      return new ResponseEntity<>(HttpStatus.OK);
//    }
//  }

  //  @RequestMapping(value = "/users/search", method = RequestMethod.GET)
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

  @PostMapping("/users/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    userDao.save(user);
    return ResponseEntity.ok(HttpStatus.OK);
  }
}
