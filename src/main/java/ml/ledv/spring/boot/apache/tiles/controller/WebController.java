package ml.ledv.spring.boot.apache.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/home")
    public String welcome(){
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

}
