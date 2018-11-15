package com.team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.team5.dao.interfaces.SortFilterCrudDao;
import com.team5.models.Vacancy;
import com.team5.models.VacancyFilter;
import com.team5.utils.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/vacancy/")
public class VacancyController {

    /**
     * Class perform sql operations.
     */
    @Autowired
    private SortFilterCrudDao<Vacancy, VacancyFilter> vacancyDao;

    /**
     * Filter.
     */
    private VacancyFilter vacancyFilter = new VacancyFilter();

    /**
     * View page with records from DB.
     * @param vacancySortField Sorting field in DB.
     * @param page page number.
     * @return page with records from DB.
     */
    @GetMapping(value = "/view/{page}/**")
    public List<Vacancy> view(
            @CookieValue(value = "vacancySortField", required = false)
            final Cookie vacancySortField,
            final @PathVariable int page) {
        final int total = 5;
        int offset = Utils.getPageOffset(page, total);

        List<Vacancy> vacancies = null;
        if (vacancySortField != null) {
            vacancies = vacancyDao.getFilteredSortedEntitiesByPage(
                    this.vacancyFilter, vacancySortField.getValue(), offset, total);
        } else {
            vacancies = vacancyDao.getFilteredEntitiesByPage(
                    this.vacancyFilter, offset, total);
        }

        int[] pages = Utils.getPagesIndexArray(vacancyDao, total);
        return vacancies;
    }

    /**
     * Set sorted field.
     * @param response Http response.
     * @param sortField Sort field value.
     * @return page with sorted records from DB.
     */
    @GetMapping(value = "/sortview/{sortField}/**")
    public List<Vacancy> addSorting(
            final HttpServletResponse response,
            @PathVariable final String sortField
    ) {
        final int lifeTime = 1000 * 60 * 60 * 24;
        final int firstPage = 1;
        Cookie cookie = new Cookie("vacancySortField", sortField);
        cookie.setMaxAge(lifeTime);
        cookie.setPath("/");
        response.addCookie(cookie);
        return view(cookie, firstPage);
    }

    /**
     * Add new vacancy to DB.
     * @param vacancy New vacancy.
     * @param result Validation result.
     * @return Redirect URL.
     */
    @PostMapping(value = "/view")
    public String addNewVacancy(
            @RequestBody @Valid final Vacancy vacancy,
            final BindingResult result
    ) {
        if (result.hasErrors()) {
            return "Error";
        }
        vacancyDao.save(vacancy);
        return "Success";
    }

    /**
     * Delete vacancy from db by id.
     * @param id Vacancy PK.
     * @return Redirect URL.
     */
    @DeleteMapping(value = "/view/{id}")
    public String delete(@PathVariable final long id) {
        vacancyDao.delete(id);
        return "Success";
    }

    /**
     * Edit vacancy.
     * @param vacancy Edit skill.
     * @param result Validation result.
     * @param id PK.
     * @return Redirect URL.
     */
    @PutMapping(value = "/view/{id}")
    public String updateSkill(
            @RequestBody @Valid final Vacancy vacancy,
            final BindingResult result,
            @PathVariable final long id
    ) {
        if (result.hasErrors()) {
            return "Error";
        }

        vacancy.setId(id);
        vacancyDao.update(vacancy);
        return "Success";
    }

    /**
     * Set filter.
     * @param filter Filter value.
     * @return Redirect URL.
     */
    @PostMapping(value = "/filter")
    public String setFilter(@RequestBody final String filter) {
        this.vacancyFilter.setPosition(filter);
        return "Success";
    }
}
