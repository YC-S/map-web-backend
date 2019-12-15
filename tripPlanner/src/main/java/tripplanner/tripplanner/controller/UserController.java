package tripplanner.tripplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
  @Autowired private UserService userService;

  @GetMapping("/register/{username}/{password}")
  public ResponseEntity<HttpStatus> Register(
      @PathVariable String username, @PathVariable String password) {
    User newUser = new User();
    userService.addUser(newUser);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  //    @PostMapping("/register")
  //    public ResponseEntity<HttpStatus> Register(@RequestBody LoginForm loginForm) {
  //      userService.addUser(loginForm);
  //      return ResponseEntity.ok(HttpStatus.OK);
  //    }
}

