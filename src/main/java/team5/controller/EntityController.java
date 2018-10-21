package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.SkillDao.SkillDao;
import team5.models.Skill;

import java.util.List;
import java.util.Locale;

@Controller
public class EntityController {

    @Autowired
    SkillDao skillDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView setUser(Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/skill/{pageid}", method = RequestMethod.GET)
    public ModelAndView setSkill(Locale locale, @PathVariable int pageid) {
        int total=5;
        if(pageid==1){}
        else{
            pageid=(pageid-1)*total+1;
        }
        List<Skill> list = skillDao.getByPage(pageid,total);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.getModelMap().addAttribute("list", list);
        modelAndView.setViewName("index");
        return modelAndView;

    }
}
