package com.team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.team5.dao.InterviewDao;
import com.team5.models.Interview;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/interview")
public class InterviewController  implements EntityController<Interview, Long> {

    @Autowired
    private InterviewDao interviewDao;

    private String filter = "";
    private String filteringField = "id";

    /**
     * View page with records from DB.
     * @param interviewSortField Sorting field in DB.
     * @param page page number.
     * @return page with records from DB.
     */
    @GetMapping(value = "/view/{page}/**")
    public List<Interview> view(
            @CookieValue(value = "interviewSortField", required = false)
            final Cookie interviewSortField,
            @PathVariable final int page
    ) {
        final int total = 5;
        int numberInDB = 1;
        if (page != 1) {
            numberInDB = (page - 1) * total + 1;
        }

        List<Interview> interviews;
        if (interviewSortField != null) {
            interviews = interviewDao.getFilteredSortedEntitiesByPage(
                    filteringField, filter,
                    interviewSortField.getValue(), numberInDB, total);
        } else {
            interviews = interviewDao.getFilteredEntitiesByPage(
                    filteringField, filter,
                    numberInDB, total);
        }

        return interviews;
    }

    /**
     * View one record from DB.
     * @param id PK.
     * @return Page with selected record.
     */
    @GetMapping(value = "/viewSkillById/{id}/**")
    public Interview viewById(@PathVariable final Long id) {
        Interview interview = interviewDao.getById(id);
        return interview;
    }

    /**
     * Set sorted field.
     * @param response Http response.
     * @param sortField Sort field value.
     * @return page with sorted records from DB.
     */
    @GetMapping(value = "/addSorting/{sortField}/**")
    public List<Interview> addSorting(
            final HttpServletResponse response,
            @PathVariable final String sortField
    ) {
        final int lifeTime = 1000 * 60 * 60 * 24;
        final int firstPage = 1;
        Cookie cookie = new Cookie("interviewSortField", sortField);
        cookie.setMaxAge(lifeTime);
        cookie.setPath("/");
        response.addCookie(cookie);
        return view(cookie, firstPage);
    }

    /**
     * Add new interview to DB.
     * @param interview New interview.
     * @param result Validation result.
     * @return Redirect URL.
     */
    @PostMapping(value = "/view")
    public String add(
            @RequestBody @Valid final Interview interview,
            final BindingResult result
    ) {
        if (result.hasErrors()) {
            return ERROR;
        }
        interviewDao.save(interview);
        return SUCCESS;
    }

    /**
     * Delete skill from db.
     * @param id Skill PK.
     * @return Redirect URL.
     */
    @DeleteMapping(value = "/view/{id}")
    public String delete(@PathVariable final Long id) {
        interviewDao.delete(id);
        return SUCCESS;
    }

    /**
     * Edit interview.
     * @param interview Edit interview.
     * @param result Validation result.
     * @param id PK.
     * @return Redirect URL.
     */
    @PutMapping(value = "/view/")
    public String update(
            @RequestBody @Valid final Interview interview,
            final BindingResult result,
            final Long id
    ) {
        if (result.hasErrors()) {
            return ERROR;
        }
        interviewDao.update(interview);
        return SUCCESS;
    }

    /**
     * Set filter.
     * @param filterInput Filter value.
     * @return Redirect URL.
     */
    @PostMapping(value = "/filter")
    public String setFilter(
            @RequestBody final String filter,
            @RequestBody final String filteringField
    ) {
        this.filter = filter;
        this.filteringField = filteringField;
        return SUCCESS;
    }
}
