package team5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Locale;
import team5.config.Candidate;

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

    @RequestMapping(value = "/candidate", method = RequestMethod.GET)
    public ModelAndView setCandidate(String type) {
        ModelAndView modelAndView = new ModelAndView();
        if(type!=null){
            modelAndView.getModelMap().addAttribute("type", type);
        }
        ArrayList<Candidate> candidates = new ArrayList<>();

        String[]users = new String[3];
        users[0]="Yana";
        users[1]="Katya";
        users[2]="Dasha";
        modelAndView.getModelMap().addAttribute("candidates", candidates);
        modelAndView.getModelMap().addAttribute("entity", "Candidate");
        modelAndView.setViewName("index");
        return modelAndView;
    }
/*
    @RequestMapping(value = "/candidate/view", method = RequestMethod.GET)
    public ModelAndView setCandidateView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("Candidate", "View");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/candidate/add", method = RequestMethod.GET)
    public ModelAndView setAdd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("Candidate", "Add");
        modelAndView.setViewName("index");
        return modelAndView;
    }
*/
}
