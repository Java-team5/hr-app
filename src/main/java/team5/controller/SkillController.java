package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.interfaces.EntityDao;
import team5.models.Skill;

import java.util.List;
import java.util.Locale;

import team5.utils.Utils;

@Controller
@RequestMapping(value ="/skill/")
public class SkillController {

    @Autowired
    EntityDao skillDao;

    @RequestMapping(value = "/{page}/**", method = RequestMethod.GET)
    public ModelAndView setSkillView(Locale locale, @PathVariable int page) {
        int total=5;

        int offset = Utils.getPageOffset(page,total);

        List<Skill> skills = skillDao.getEntitiesByPage(offset,total);

        int[] pages = Utils.getPagesIndexArray(skillDao,total);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.getModelMap().addAttribute("skill", skills);
        modelAndView.getModelMap().addAttribute("pages", pages);
        modelAndView.setViewName("index");
        return modelAndView;

    }

}
