package team5.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import team5.dao.InterviewDao;
import team5.models.Interview;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterviewControllerTest {

    private final String fieldName = "interviev";
    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";

    @Mock
    private InterviewDao dao;
    @InjectMocks
    private InterviewController controller;

    @Test
    public void getFilteredEntitiesByPage() {
        List<Interview> interviews = new ArrayList<>();
        interviews.add(new Interview(0, 1, 1, new Date(), new Date()));
        when(dao.getFilteredEntitiesByPage("id", "", 1, 5))
                .thenReturn(interviews);
        List<Interview> result = controller.view(null, 1);
        assertEquals(interviews, result);
    }

    @Test
    public void getFilteredSortedEntitiesByPage() {
        List<Interview> interviews = new ArrayList<>();
        interviews.add(new Interview(0, 1, 1, new Date(), new Date()));
        when(dao.getFilteredSortedEntitiesByPage("id", "", "id", 6, 5))
                .thenReturn(interviews);
        List<Interview> result = controller.view(new Cookie("interviewSortField", "id"), 2);
        assertEquals(interviews, result);
    }

    @Test
    public void interviewViewById() {
        Interview interview = new Interview(0, 1, 1, new Date(), new Date());
        when(dao.getById(1)).thenReturn(interview);
        Interview result = controller.viewById(1);
        assertEquals(interview, result);
    }

    @Test
    public void interviewAddSorting() {
        List<Interview> interviews = new ArrayList<>();
        interviews.add(new Interview(0, 1, 1, new Date(), new Date()));
        when(dao.getFilteredSortedEntitiesByPage("id", "", fieldName, 1, 5))
                .thenReturn(interviews);
        HttpServletResponse response = mock(HttpServletResponse.class);
        List<Interview> result = controller.addSorting(response, fieldName);
        assertEquals(interviews, result);
    }

    @Test
    public void addNewInterviewWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        String url = controller.addNewInterview(new Interview(0, 1, 1, new Date(), new Date()), result);
        assertEquals(url, ERROR);
    }

    @Test
    public void addNewInterviewWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.addNewInterview(new Interview(0, 1, 1, new Date(), new Date()), result);
        assertEquals(url, SUCCESS);
    }

    @Test
    public void deleteInterview() {
        BindingResult result = mock(BindingResult.class);
        String url = controller.deleteInterview(1);
        assertEquals(url, SUCCESS);
    }

    @Test
    public void updateInterviewWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);
        String url = controller.updateInterview(new Interview(0, 1, 1, new Date(), new Date()), result);
        assertEquals(url, ERROR);
    }

    @Test
    public void updateInterviewWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.updateInterview(new Interview(0, 1, 1, new Date(), new Date()), result);
        assertEquals(url, SUCCESS);
    }

    @Test
    public void interviewSetFilter() {
        String url = controller.setFilter("", "");
        assertEquals(url, SUCCESS);
    }

}