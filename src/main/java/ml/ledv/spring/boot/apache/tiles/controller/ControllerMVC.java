package ml.ledv.spring.boot.apache.tiles.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerMVC {

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
