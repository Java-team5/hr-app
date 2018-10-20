package team5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import team5.MavenVersion;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView initPath(Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("version", MavenVersion.getVersion());
        modelAndView.setViewName("index");

        return modelAndView;
    }

}

