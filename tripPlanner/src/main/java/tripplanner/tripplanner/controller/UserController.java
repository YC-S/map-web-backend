package tripplanner.tripplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.ItemService;
import tripplanner.tripplanner.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	    @Autowired
	    private UserService userService;
	  
	    @PostMapping("/addUser")
	    public void addUser(User user) {
	    	userService.addUser(user);
	    }
	    
	    @PutMapping("/addUser")
	    public void updateItem(User user) {
	    	userService.addUser(user);
	    }
	    
	    @GetMapping("/users/{userId}")
	    public User getUserById(@PathVariable String userId) {
	    	User theUser = userService.getUserByUserId(userId);
	    	return theUser;
	    }
	    
	    @DeleteMapping("/users/{userId}")
	    public String deleteItemById(@PathVariable String userId) {
	    	User theUser = userService.getUserByUserId(userId);
			if(theUser == null) {
				throw new RuntimeException("User id not found - " + userId);
			}
			userService.deleteUser(userId);	
			return "Deleted user id - " + userId;
	    }  
}
