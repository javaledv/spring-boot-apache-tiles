package ml.ledv.spring.boot.apache.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
