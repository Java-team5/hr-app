package team5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class EntityController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView setUser(String type) {
        ModelAndView modelAndView = new ModelAndView();
        if(type!=null){
            modelAndView.getModelMap().addAttribute("type", type);
        }
        String[]users=new String[3];
        users[0]="Yana";
        users[1]="Katya";
        users[2]="Dasha";
        modelAndView.getModelMap().addAttribute("users", users);
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
