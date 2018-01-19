package ml.ledv.spring.boot.apache.tiles.controller.mvc;

import ml.ledv.spring.boot.apache.tiles.utils.Routing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class HomeController {

    @GetMapping("/")
    public ModelAndView getHomePage() {
        return new ModelAndView("rootPage");
    }

    @GetMapping("/error-page")
    public ModelAndView error(@RequestParam("errMsg") final String errMsg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errMsg", errMsg);
        return modelAndView;
    }
}
