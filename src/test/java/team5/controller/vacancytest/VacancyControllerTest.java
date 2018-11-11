package team5.controller.vacancytest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.validation.BindingResult;
import team5.controller.VacancyController;
import team5.controller.vacancytest.utils.VacancyComparatorByPosition;
import team5.dao.Vacancy.VacancyDao;
import team5.models.Vacancy;
import team5.utils.ActionResult;
import team5.utils.SqlFilter;

import javax.servlet.http.Cookie;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class VacancyControllerTest {

    private List<Vacancy> vacancyList;
    private SqlFilter filter = new SqlFilter();

    @Spy
    private VacancyDao dao = new VacancyDao();

    @InjectMocks
    private VacancyController controller;

    @Before
    public void init() {
        this.vacancyList = this.dao.getAll();
        this.filter.clear();
    }

    @Test
    public void getFilteredSortedEntitiesByPage() {
        this.filter.put("position","Java");

        doReturn(this.vacancyList.subList(0,0)).when(dao).
                getFilteredSortedEntitiesByPage(this.filter,1,5);

        this.controller.setFilter(this.filter);
        List<Vacancy> filteredVacancyList = this.controller.view(null,1);
        assertEquals(this.vacancyList.subList(0,0), filteredVacancyList);

        filteredVacancyList = this.controller.view(
                new Cookie("vacancySortField","position"),1);

        assertEquals(this.vacancyList.subList(0,0), filteredVacancyList);
    }

    @Test
    public void getSortedEntitiesByPage() {
        this.vacancyList.sort(new VacancyComparatorByPosition());

        doReturn(this.vacancyList).when(dao).
                getFilteredSortedEntitiesByPage(this.filter,1,5);

        List<Vacancy> sortedVacancyList = this.controller.view(
                new Cookie("vacancySortField","position"),1);
        assertEquals(this.vacancyList, sortedVacancyList);
    }

    @Test
    public void addNewVacancylWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);

        String url = controller.addNewVacancy(
                new Vacancy(1, 1, "Ruby developer",
                        1000.0, 1500.0, "Close",
                        1.5), result);

        assertEquals(url, ActionResult.ERROR.toString());
    }

    @Test
    public void addNewVacancylWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);

        String url = controller.addNewVacancy(
                new Vacancy(1, 1, "Ruby developer",
                        1000.0, 1500.0, "Close",
                        1.5), result);

        assertEquals(url, ActionResult.SUCCESS.toString());
    }


    @Test
    public void deleteSkill() {
        assertEquals(ActionResult.SUCCESS.toString(), this.controller.delete(4));
        assertEquals(null, this.controller.viewById(4));
    }

    @Test
    public void updateEntityWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);

        Vacancy vacancy = dao.getById(1);
        vacancy.setSalaryFrom(5000);
        vacancy.setSalaryTo(10000);
        vacancy.setSalaryTo(5);

        String url = controller.updateVacancy(vacancy,result,1);
        assertEquals(url, ActionResult.SUCCESS.toString());
        assertEquals(vacancy, dao.getById(1));
    }

    @Test
    public void updateEntityWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);

        Vacancy vacancy = dao.getById(1);
        vacancy.setSalaryFrom(5000);
        vacancy.setSalaryTo(10000);
        vacancy.setSalaryTo(5);

        String url = controller.updateVacancy(vacancy,result,1);
        assertEquals(url, ActionResult.ERROR.toString());
        assertEquals(vacancy, dao.getById(1));
    }
}
