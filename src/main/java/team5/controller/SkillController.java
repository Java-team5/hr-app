package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
@RestController
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
    @GetMapping(value = "/view/{page}/**")
    public List<Skill> skillView(
            @CookieValue(value = "skillSortField", required = false)
            final Cookie skillSortField,
            @PathVariable final int page
    ) {
        final int total = 5;
        int numberInDB = 1;
        if (page != 1) {
            numberInDB = (page - 1) * total + 1;
        }

        List<Skill> skills;
        final String fiedName = "skill";
        if (skillSortField != null) {
            skills = skillDao.getFilteredSortedEntitiesByPage(
                    fiedName, filter.getSkill(),
                    skillSortField.getValue(), numberInDB, total);
        } else {
            skills = skillDao.getFilteredEntitiesByPage(
                    fiedName, filter.getSkill(),
                    numberInDB, total);
        }

        float pagesCount = (float) skillDao.count(filter.getSkill()) / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i + 1;
        }
        return skills;
    }

    /**
     * View one record from DB.
     * @param id Skill PK.
     * @return Page with selected record.
     */
    @GetMapping(value = "/viewSkillById/{id}/**")
    public Skill skillViewById(@PathVariable final String id) {
        Skill skill = skillDao.getById(id);
        return skill;
    }

    /**
     * Set sorted field.
     * @param response Http response.
     * @param sortField Sort field value.
     * @return page with sorted records from DB.
     */
    @GetMapping(value = "/addSorting/{sortField}/**")
    public List<Skill> skillAddSorting(
            final HttpServletResponse response,
            @PathVariable final String sortField
    ) {
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
    @PostMapping(value = "/view")
    public String addNewSkill(
            @RequestBody @Valid final Skill skill,
            final BindingResult result
    ) {
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
    @DeleteMapping(value = "/view/{id}")
    public String deleteSkill(@PathVariable final String id) {
        skillDao.delete(id);
        return "redirect:/skill/view/1";
    }

    /**
     * Edit skill.
     * @param skill Edit skill.
     * @param result Validation result.
     * @param id PK.
     * @return Redirect URL.
     */
    @PutMapping(value = "/view/{id}")
    public String updateSkill(
            @RequestBody @Valid final Skill skill,
            final BindingResult result,
            @PathVariable final String id
    ) {
        if (result.hasErrors()) {
            return "redirect:/skill/updateSkill/" + skill.getSkill();
        }
        skillDao.update(skill, id);
        return "redirect:/skill/view/1";
    }

    /**
     * Set filter.
     * @param filterInput Filter value.
     * @return Redirect URL.
     */
    @PostMapping(value = "/filter")
    public String skillSetFilter(@RequestBody final SkillFilter filterInput) {
        filter.setSkill(filterInput.getSkill());
        return "redirect:/skill/view/1";
    }
}
