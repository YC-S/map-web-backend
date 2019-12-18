package tripplanner.tripplanner.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.HibernateSearchService;
import tripplanner.tripplanner.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  @Autowired private UserDao userDao;

  @Autowired private HibernateSearchService searchService;

  @Autowired private UserService userService;

  @PostMapping("/users/login")
  public ResponseEntity<?> validateLogin(@RequestBody User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String search(@RequestParam(value = "search", required = false) String q, Model model) {
    List<User> searchResults = null;
    try {
      userService.addUser();
      searchResults = searchService.fuzzySearch(q);
    } catch (Exception ex) {
      // here you should handle unexpected errors
      // ...
      // throw ex;
    }
    model.addAttribute("search", searchResults);
    return "index";
  }

  @PostMapping("/users/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    userDao.save(user);
    return ResponseEntity.ok(HttpStatus.OK);
  }
}
