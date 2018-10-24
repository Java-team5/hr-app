package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.FeedbackDAO.FeedbackDAO;
import team5.dao.SkillDao.SkillDao;
import team5.dao.UserDao.UserDao;
import team5.models.Feedback;
import team5.models.Skill;
import team5.models.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import team5.config.Candidate;

import static java.lang.Math.ceil;

@Controller
public class EntityController {

    @Autowired
    SkillDao skillDao;

    @Autowired
    UserDao userDao;

    @Autowired
    FeedbackDAO feedbackDAO;

    @RequestMapping(value = "/feedback/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setFeedbackView(Locale locale, @PathVariable int page) {
        int total=5;
        if(page==1){}
        else{
            page=(page-1)*total+1;
        }
        List<Feedback> feedbacks = feedbackDAO.getEntitiesByPage(page,total);
        float pagesCount = (float) feedbackDAO.getCount() / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for(int i=0; i<pages.length; i++){
            pages[i] = i + 1;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "Feedback");
        modelAndView.getModelMap().addAttribute("feedback", feedbacks);
        modelAndView.getModelMap().addAttribute("pages", pages);

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/feedback/add", method = RequestMethod.GET)
    public ModelAndView serFeedbackAdd(Locale locale){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Feedback");
        modelAndView.getModelMap().addAttribute("type", "add");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/user/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setUserView(Locale locale, @PathVariable int page) {
        int total=5;
        if(page==1){}
        else{
            page=(page-1)*total+1;
        }
        List<User> users = userDao.getEntitiesByPage(page,total);
        float pagesCount = (float) userDao.getCount() / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for(int i=0; i<pages.length; i++){
            pages[i] = i + 1;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.getModelMap().addAttribute("user", users);
        modelAndView.getModelMap().addAttribute("pages", pages);

        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public ModelAndView serUserAdd(Locale locale){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.getModelMap().addAttribute("type", "add");
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
