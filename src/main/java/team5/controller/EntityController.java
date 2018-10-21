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

import static java.lang.Math.ceil;

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

    @RequestMapping(value = "/skill/{page}/**", method = RequestMethod.GET)
    public ModelAndView setSkill(Locale locale, @PathVariable int page) {
        int total=5;
        if(page==1){}
        else{
            page=(page-1)*total+1;
        }
        List<Skill> skills = skillDao.getEntitiesByPage(page,total);

        float pagesCount = (float) skillDao.getCount() / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for(int i=0; i<pages.length; i++){
            pages[i] = i + 1;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.getModelMap().addAttribute("skill", skills);
        modelAndView.getModelMap().addAttribute("pages", pages);
        modelAndView.setViewName("index");
        return modelAndView;

    }

}
