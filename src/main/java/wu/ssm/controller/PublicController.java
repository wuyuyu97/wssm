package wu.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublicController {

    @GetMapping("/alert")
    public ModelAndView alert() {
        ModelAndView m = new ModelAndView("public/alert");
        return m;
    }

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView m = new ModelAndView("user/login");
        return m;
    }

    @GetMapping("/register")
    public ModelAndView registerView() {
        ModelAndView m = new ModelAndView("user/register");
        return m;
    }


    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/index");
    }


}
