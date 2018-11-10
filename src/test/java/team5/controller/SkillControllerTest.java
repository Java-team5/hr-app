package team5.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import team5.dao.SkillDao;
import team5.models.Skill;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillControllerTest {

    private final String fieldName = "skill";
    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";

    @Mock
    private SkillDao dao;
    @InjectMocks
    private SkillController controller;

    @Test
    public void getFilteredEntitiesByPage() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java"));
        when(dao.getFilteredEntitiesByPage(fieldName, "", 1, 5))
                .thenReturn(skills);
        List<Skill> skillList = controller.view(null, 1);
        assertEquals(skills, skillList);
    }

    @Test
    public void getFilteredSortedEntitiesByPage() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("C#"));
        when(dao.getFilteredSortedEntitiesByPage(fieldName, "", fieldName, 6, 5))
                .thenReturn(skills);
        List<Skill> skillList = controller.view(new Cookie("skillSortField", "skill"), 2);
        assertEquals(skills, skillList);
    }

    @Test
    public void skillViewById() {
        Skill skill = new Skill("PHP");
        when(dao.getById("PHP"))
                .thenReturn(skill);
        Skill returnSkill = controller.viewById("PHP");
        assertEquals(skill, returnSkill);
    }

    @Test
    public void skillAddSorting() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("C"));
        when(dao.getFilteredSortedEntitiesByPage(fieldName, "", fieldName, 1, 5))
                .thenReturn(skills);
        HttpServletResponse response = mock(HttpServletResponse.class);
        List<Skill> skillList = controller.addSorting(response, fieldName);
        assertEquals(skills, skillList);
    }

    @Test
    public void addNewSkillWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);
        String url = controller.add(new Skill("C++"), result);
        assertEquals(url, ERROR);
    }

    @Test
    public void addNewSkillWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.add(new Skill("C++"), result);
        assertEquals(url, SUCCESS);
    }

    @Test
    public void deleteSkill() {
        BindingResult result = mock(BindingResult.class);
        String url = controller.delete("JS");
        assertEquals(url, SUCCESS);
    }

    @Test
    public void updateSkillWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);
        String url = controller.update(new Skill("C++"), result, "Spring");
        assertEquals(url, ERROR);
    }

    @Test
    public void updateSkillWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.update(new Skill("C++"), result, "Spring");
        assertEquals(url, SUCCESS);
    }

    @Test
    public void skillSetFilter() {
        String url = controller.setFilter("");
        assertEquals(url, SUCCESS);
    }

}