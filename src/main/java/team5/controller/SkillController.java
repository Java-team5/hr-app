package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.Skill.SkillDao;
import team5.models.Filter;
import team5.models.Skill;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static java.lang.Math.ceil;

@Controller
@RequestMapping(value = "/skill")
public class SkillController {

    @Autowired
    SkillDao skillDao;
    Filter filter = new Filter();

    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView skillView(@CookieValue(value = "skillSortField", required = false) Cookie skillSortField, @PathVariable int page) {
        int total=5;
        if(page==1){}
        else{
            page=(page-1)*total+1;
        }

        List<Skill> skills;
        if(skillSortField != null)
            skills= skillDao.getSortedEntitiesByPage(filter.getValue(), skillSortField.getValue(), page, total);
        else
            skills= skillDao.getEntitiesByPage(filter.getValue(), page, total);

        float pagesCount = (float) skillDao.count(filter.getValue()) / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for(int i=0; i<pages.length; i++){
            pages[i] = i + 1;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("skill", skills);
        modelAndView.getModelMap().addAttribute("pages", pages);
        modelAndView.addObject("filterInput", filter);
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView skillAdd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.getModelMap().addAttribute("type", "add");
        modelAndView.addObject("addSkillForm", new Skill());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/addSorting/{sortField}/**", method = RequestMethod.GET)
    public ModelAndView skillAddSorting(HttpServletResponse response, @PathVariable String sortField) {

        Cookie cookie = new Cookie("skillSortField", sortField);
        cookie.setMaxAge(1000*60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);

        return skillView(cookie,1);
    }

    @RequestMapping(value = "/addSkill", method = RequestMethod.POST)
    public String addNewSkill(@ModelAttribute("addSkillForm") @Valid Skill skill, BindingResult result){
        if(result.hasErrors()) {
            return "redirect:/skill/add";
        }
        skillDao.save(skill);
        return "redirect:/skill/view/1";
    }
    @RequestMapping(value = "/deleteSkill/{id}", method = RequestMethod.GET)
    public String deleteSkill(@PathVariable int id){
        skillDao.delete(id);
        return "redirect:/skill/view/1";
    }
    @RequestMapping(value = "/updateSkill/{id}", method = RequestMethod.GET)
    public ModelAndView updateSkillGet(@PathVariable int id){
        Skill skill = skillDao.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("skill", skill);
        modelAndView.setViewName("SkillEditForm");
        return modelAndView;
    }

    @RequestMapping(value = "/updateSaveSkill", method = RequestMethod.POST)
    public String updateSkillPost(@ModelAttribute("editSkillForm") @Valid Skill skill, BindingResult result){
        if(result.hasErrors()) {
            return "redirect:/skill/updateSkill/" + skill.getId();
        }
        skillDao.update(skill);
        return "redirect:/skill/view/1";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String skillSetFilter(@ModelAttribute("filterInput") Filter filterInput){
        filter.setValue(filterInput.getValue());
        return "redirect:/skill/view/1";
    }

}
