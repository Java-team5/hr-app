package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import team5.dao.SkillDao;
import team5.models.Skill;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {

    @Autowired
    private SkillDao skillDao;

    /**
     * Filtering value.
     */
    private String filter = "";
    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";

    /**
     * View page with records from DB.
     * @param skillSortField Sorting field in DB.
     * @param page page number.
     * @return page with records from DB.
     */
    @GetMapping(value = "/view/{page}/**")
    public List<Skill> view(
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
        final String fieldName = "skill";
        if (skillSortField != null) {
            skills = skillDao.getFilteredSortedEntitiesByPage(
                    fieldName, filter,
                    skillSortField.getValue(), numberInDB, total);
        } else {
            skills = skillDao.getFilteredEntitiesByPage(
                    fieldName, filter,
                    numberInDB, total);
        }

        return skills;
    }

    /**
     * View one record from DB.
     * @param id Skill PK.
     * @return Page with selected record.
     */
    @GetMapping(value = "/viewSkillById/{id}/**")
    public Skill viewById(@PathVariable final String id) {
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
    public List<Skill> addSorting(
            final HttpServletResponse response,
            @PathVariable final String sortField
    ) {
        final int lifeTime = 1000 * 60 * 60 * 24;
        final int firstPage = 1;
        Cookie cookie = new Cookie("skillSortField", sortField);
        cookie.setMaxAge(lifeTime);
        cookie.setPath("/");
        response.addCookie(cookie);
        return view(cookie, firstPage);
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
            return ERROR;
        }
        skillDao.save(skill);
        return SUCCESS;
    }

    /**
     * Delete skill from db.
     * @param id Skill PK.
     * @return Redirect URL.
     */
    @DeleteMapping(value = "/view/{id}")
    public String deleteSkill(@PathVariable final String id) {
        skillDao.delete(id);
        return SUCCESS;
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
            return ERROR;
        }
        skillDao.update(skill, id);
        return SUCCESS;
    }

    /**
     * Set filter.
     * @param filterInput Filter value.
     * @return Redirect URL.
     */
    @PostMapping(value = "/filter")
    public String setFilter(@RequestBody final String filter) {
        this.filter = filter;
        return SUCCESS;
    }
}
