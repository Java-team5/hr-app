package com.team5.controller;

import com.team5.dao.CandidateDao;
import com.team5.models.Candidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CandidateControllerTest {

    private final String fieldName = "candidate";
    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";

    @Mock
    private CandidateDao dao;
    @InjectMocks
    private CandidateController controller;

    @Test
    public void getFilteredEntitiesByPage() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate(0, "qwerty", "asdfg", new Date(), 7788));
        when(dao.getFilteredEntitiesByPage("ID", "", 1, 5))
                .thenReturn(candidates);
        List<Candidate> result = controller.view(null, 1);
        assertEquals(candidates, result);
    }

    @Test
    public void getFilteredSortedEntitiesByPage() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate(0, "qwerty", "asdfg", new Date(), 7788));
        when(dao.getFilteredSortedEntitiesByPage("ID", "", "ID", 6, 5))
                .thenReturn(candidates);
        List<Candidate> result = controller
                .view(new Cookie("candidateSortField", "ID"), 2);
        assertEquals(candidates, result);
    }

    @Test
    public void viewById() {
        Candidate candidate = new Candidate(0, "qwerty", "asdfg", new Date(), 7788);
        when(dao.getById(1)).thenReturn(candidate);
        Candidate result = controller.viewById((long) 1);
        assertEquals(candidate, result);
    }

    @Test
    public void addSorting() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate(0, "qwerty", "asdfg", new Date(), 7788));
        when(dao.getFilteredSortedEntitiesByPage("ID", "", fieldName, 1, 5))
                .thenReturn(candidates);
        HttpServletResponse response = mock(HttpServletResponse.class);
        List<Candidate> result = controller.addSorting(response, fieldName);
        assertEquals(candidates, result);
    }

    @Test
    public void addWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        String url = controller.add(new Candidate(0, "qwerty", "asdfg", new Date(), 7788), result);
        assertEquals(url, ERROR);
    }

    @Test
    public void addWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.add(new Candidate(0, "qwerty", "asdfg", new Date(), 7788), result);
        assertEquals(url, SUCCESS);
    }

    @Test
    public void delete() {
        BindingResult result = mock(BindingResult.class);
        String url = controller.delete((long) 1);
        assertEquals(url, SUCCESS);
    }

    @Test
    public void updateWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);
        String url = controller.update(new Candidate(0, "qwerty", "asdfg", new Date(), 7788), result, (long) 0);
        assertEquals(url, ERROR);
    }

    @Test
    public void updateWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.update(new Candidate(0, "qwerty", "asdfg", new Date(), 7788), result, (long) 0);
        assertEquals(url, SUCCESS);
    }

    @Test
    public void setFilter() {
        String url = controller.setFilter("", "");
        assertEquals(url, SUCCESS);
    }

}