package team5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class EntityController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView setUser(Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
