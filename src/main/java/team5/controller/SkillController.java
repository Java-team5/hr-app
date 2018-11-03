package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.Skill.SkillDao;
import team5.models.Skill;
import team5.models.SkillFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static java.lang.Math.ceil;

/**
 * Controller by url /skill.
 */
@Controller
@RequestMapping(value = "/skill")
public class SkillController {

    /**
     * Dao for entity 'Skill'.
     */
    @Autowired
    private SkillDao skillDao;

    /**
     * Filtering value.
     */
    private SkillFilter filter = new SkillFilter();

    /**
     * View page with records from DB.
     * @param skillSortField Sorting field in DB.
     * @param page page number.
     * @return page with records from DB.
     */
    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView skillView(@CookieValue(value = "skillSortField", required = false) final Cookie skillSortField, @PathVariable final int page) {
        final int total = 5;
        int numberInDB = 1;
        if (page != 1) {
            numberInDB = (page - 1) * total + 1;
        }

        List<Skill> skills;
        final String fiedName = "skill";
        if (skillSortField != null) {
            skills = skillDao.getFilteredSortedEntitiesByPage(fiedName, filter.getSkill(), skillSortField.getValue(), numberInDB, total);
        } else {
            skills = skillDao.getFilteredEntitiesByPage(fiedName, filter.getSkill(), numberInDB, total);
        }

        float pagesCount = (float) skillDao.count(filter.getSkill()) / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for (int i = 0; i < pages.length; i++) {
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

    /**
     * View add form.
     * @return page with add form.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView skillAdd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.getModelMap().addAttribute("type", "add");
        modelAndView.addObject("addSkillForm", new Skill());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * Set sorted field.
     * @param response Http response.
     * @param sortField Sort field value.
     * @return page with sorted records from DB.
     */
    @RequestMapping(value = "/addSorting/{sortField}/**", method = RequestMethod.GET)
    public ModelAndView skillAddSorting(final HttpServletResponse response, @PathVariable final String sortField) {
        final int lifeTime = 1000 * 60 * 60 * 24;
        final int firstPage = 1;
        Cookie cookie = new Cookie("skillSortField", sortField);
        cookie.setMaxAge(lifeTime);
        cookie.setPath("/");
        response.addCookie(cookie);
        return skillView(cookie, firstPage);
    }

    /**
     * Add new skill to DB.
     * @param skill New skill.
     * @param result Validation result.
     * @return Redirect URL.
     */
    @RequestMapping(value = "/addSkill", method = RequestMethod.POST)
    public String addNewSkill(@ModelAttribute("addSkillForm") @Valid final Skill skill, final BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/skill/add";
        }
        skillDao.save(skill);
        return "redirect:/skill/view/1";
    }

    /**
     * Delete skill from db.
     * @param id Skill PK.
     * @return Redirect URL.
     */
    @RequestMapping(value = "/deleteSkill/{id}", method = RequestMethod.GET)
    public String deleteSkill(@PathVariable final String id) {
        skillDao.delete(id);
        return "redirect:/skill/view/1";
    }

    /**
     * View edit form.
     * @param id Skill PK.
     * @return Page with edit form.
     */
    @RequestMapping(value = "/updateSkill/{id}", method = RequestMethod.GET)
    public ModelAndView updateSkillGet(@PathVariable final String id) {
        Skill skill = skillDao.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "edit");
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.addObject("editSkillForm", skill);
        modelAndView.setViewName("SkillEditForm");
        modelAndView.addObject("editValue", skill.getSkill());
        return modelAndView;
    }

    /**
     * Edit skill.
     * @param skill Edit skill.
     * @param result Validation result.
     * @return Redirect URL.
     */
    @RequestMapping(value = "/updateSaveSkill/{editValue}", method = RequestMethod.POST)
    public String updateSkillPost(@ModelAttribute("editSkillForm") @Valid final Skill skill, final BindingResult result, @PathVariable final String editValue) {
        if (result.hasErrors()) {
            return "redirect:/skill/updateSkill/" + skill.getSkill();
        }
        skillDao.update(skill, editValue);
        return "redirect:/skill/view/1";
    }

    /**
     * Set filter.
     * @param filterInput Filter value.
     * @return Redirect URL.
     */
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String skillSetFilter(@ModelAttribute("filterInput") final SkillFilter filterInput) {
        filter.setSkill(filterInput.getSkill());
        return "redirect:/skill/view/1";
    }

    /**
     * View one record from DB.
     * @param id Skill PK.
     * @return Page with selected record.
     */
    @RequestMapping(value = "/viewSkillById/{id}/**", method = RequestMethod.GET)
    public ModelAndView skillViewById(@PathVariable final String id) {
        ModelAndView modelAndView = new ModelAndView();
        Skill skill = skillDao.getById(id);
        modelAndView.getModelMap().addAttribute("entity", "Skill");
        modelAndView.getModelMap().addAttribute("type", "viewById");
        modelAndView.getModelMap().addAttribute("skill", skill.getSkill());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
