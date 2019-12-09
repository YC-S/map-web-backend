package tripplanner.tripplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        return "Hello World";
    }
}
