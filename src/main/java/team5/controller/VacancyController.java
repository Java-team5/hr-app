package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.exceptions.DeleteException;
import team5.dao.interfaces.SortFilterCrudDao;
import team5.models.Vacancy;
import team5.models.VacancyFilter;
import team5.utils.ActionResult;
import team5.utils.CookieValueType;
import team5.utils.SqlFilter;
import team5.utils.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.CookieStore;
import java.sql.ResultSet;
import java.util.List;

@RestController
@RequestMapping(value = "/vacancy/")
public class VacancyController {

    /**
     * Class perform sql operations.
     */
    @Autowired
    private SortFilterCrudDao<Vacancy, SqlFilter> vacancyDao;

    /**
     * Filter.
     */
    private SqlFilter filter = new SqlFilter();

    /**
     * Get current filter.
     */
    public SqlFilter getFilter() {
        return filter;
    }

    /**
     * Set current filter
     * @param filterValue new filter
     * @throws IllegalArgumentException
     * null value is not valid
     */
    public void setFilter(final SqlFilter filterValue)
            throws IllegalArgumentException {
        if (filterValue == null) {
            throw new IllegalArgumentException();
        }
        this.filter = filterValue;
    }

    /**
     * View page with records from DB.
     * @param vacancySortField http request.
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

        if (vacancySortField != null) {
            this.filter.setSortingСriterion(vacancySortField.getValue());
        }
        List<Vacancy> vacancies = null;
        vacancies = vacancyDao.getFilteredSortedEntitiesByPage(
                this.filter, offset, total);

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
     * View one record from DB.
     * @param id Vacancy PK.
     * @return Page with selected record.
     */
    @GetMapping(value = "/viewVacancyById/{id}/**")
    public Vacancy viewById(@PathVariable final long id) {
        return vacancyDao.getById(id);
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
            return ActionResult.ERROR.toString();
        }
        vacancyDao.save(vacancy);
        return ActionResult.SUCCESS.toString();
    }

    /**
     * Delete vacancy from db by id.
     * @param id Vacancy PK.
     * @return Redirect URL.
     */
    @DeleteMapping(value = "/view/{id}")
    public String delete(@PathVariable final long id) {
        try {
            vacancyDao.delete(id);
        } catch (DeleteException ex) {
            return ActionResult.ERROR.toString();
        }
        return ActionResult.SUCCESS.toString();
    }

    /**
     * Edit vacancy.
     * @param vacancy Edit skill.
     * @param result Validation result.
     * @param id PK.
     * @return Redirect URL.
     */
    @PutMapping(value = "/view/{id}")
    public String updateVacancy(
            @RequestBody @Valid final Vacancy vacancy,
            final BindingResult result,
            @PathVariable final long id
    ) {
        if (result.hasErrors()) {
            return ActionResult.ERROR.toString();
        }
        vacancy.setId(id);
        vacancyDao.update(vacancy);
        return ActionResult.SUCCESS.toString();
    }

    /**
     * Set filter.
     * @param request http request.
     * @return Redirect URL.
     */
    @PostMapping(value = "/filter")
    public String setFilter(final HttpServletRequest request) {
        this.filter.clear();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            this.filter.put(cookie.getName(), cookie.getValue());
        }
        return ActionResult.SUCCESS.toString();
    }
}
