package com.team5.controller;

import com.team5.dao.interfaces.FilteredEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.team5.models.Candidate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/candidate")
public class CandidateController implements EntityController<Candidate, Long> {

    @Autowired
    private FilteredEntityDao<Candidate> candidateDao;

    private String filter = "";
    private String filteringField = "ID";

    /**
     * View page with records from DB.
     * @param candidateSortField Sorting field in DB.
     * @param page page number.
     * @return page with records from DB.
     */
    @GetMapping(value = "/view/{page}/**")
    public List<Candidate> view(
            @CookieValue(value = "candidateSortField", required = false)
            final Cookie candidateSortField,
            @PathVariable final int page
    ) {
        final int total = 5;
        int numberInDB = 1;
        if (page != 1) {
            numberInDB = (page - 1) * total + 1;
        }

        List<Candidate> candidates;
        if (candidateSortField != null) {
            candidates = candidateDao.getFilteredSortedEntitiesByPage(
                    filteringField, filter,
                    candidateSortField.getValue(), numberInDB, total);
        } else {
            candidates = candidateDao.getFilteredEntitiesByPage(
                    filteringField, filter,
                    numberInDB, total);
        }
        return candidates;
    }

    /**
     * View one record from DB.
     * @param id PK.
     * @return Page with selected record.
     */
    @GetMapping(value = "/viewById/{id}/**")
    public Candidate viewById(@PathVariable final Long id) {
        Candidate candidate = candidateDao.getById(id);
        return candidate;
    }

    /**
     * Set sorted field.
     * @param response Http response.
     * @param sortField Sort field value.
     * @return page with sorted records from DB.
     */
    @GetMapping(value = "/addSorting/{sortField}/**")
    public List<Candidate> addSorting(
            final HttpServletResponse response,
            @PathVariable final String sortField
    ) {
        final int lifeTime = 1000 * 60 * 60 * 24;
        final int firstPage = 1;
        Cookie cookie = new Cookie("candidateSortField", sortField);
        cookie.setMaxAge(lifeTime);
        cookie.setPath("/");
        response.addCookie(cookie);
        return view(cookie, firstPage);
    }

    /**
     * Add new candidate to DB.
     * @param candidate New candidate.
     * @param result Validation result.
     * @return Redirect URL.
     */
    @PostMapping(value = "/")
    public String add(
            @RequestBody @Valid final Candidate candidate,
            final BindingResult result
    ) {
        if (result.hasErrors()) {
            return ERROR;
        }
        candidateDao.save(candidate);
        return SUCCESS;
    }

    /**
     * Delete skill from db.
     * @param id Skill PK.
     * @return Redirect URL.
     */
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable final Long id) {
        candidateDao.delete(id);
        return SUCCESS;
    }

    /**
     * Edit candidate.
     * @param candidate Edit candidate.
     * @param result Validation result.
     * @param id PK.
     * @return Redirect URL.
     */
    @PutMapping(value = "/")
    public String update(
            @RequestBody @Valid final Candidate candidate,
            final BindingResult result,
            final Long id
    ) {
        if (result.hasErrors()) {
            return ERROR;
        }
        candidateDao.update(candidate);
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
