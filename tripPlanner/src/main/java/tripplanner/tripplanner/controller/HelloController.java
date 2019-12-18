package tripplanner.tripplanner.controller;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }

}
>>>>>>> 7fda969a8fdeb88ab0ab9a6eacc0b90f16bc3a0a
