package team5.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import team5.dao.Skill.SkillDao;
import team5.models.Skill;
import team5.models.SkillFilter;

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

    @Mock
    private SkillDao dao;
    @InjectMocks
    SkillController controller;
/*

    @Before
    public void SetUp() throws Exception{
        //controller = new SkillController();

        //dao = mock(SkillDao.class);
    }
*/

    @Test
    public void getFilteredEntitiesByPage() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java"));
        when(dao.getFilteredEntitiesByPage(fieldName,"", 1, 5))
                .thenReturn(skills);
        List<Skill> skillList = controller.skillView(null, 1);
        assertEquals(skills, skillList);
    }

    @Test
    public void getFilteredSortedEntitiesByPage() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("C#"));
        when(dao.getFilteredSortedEntitiesByPage(fieldName,"", fieldName, 6, 5))
                .thenReturn(skills);
        List<Skill> skillList = controller.skillView(new Cookie("skillSortField", "skill"), 2);
        assertEquals(skills, skillList);
    }

    @Test
    public void skillViewById() {
        Skill skill = new Skill("PHP");
        when(dao.getById("PHP"))
                .thenReturn(skill);
        Skill returnSkill = controller.skillViewById("PHP");
        assertEquals(skill, returnSkill);
    }

    @Test
    public void skillAddSorting() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("C"));
        when(dao.getFilteredSortedEntitiesByPage(fieldName,"", fieldName, 1, 5))
                .thenReturn(skills);
        HttpServletResponse response = mock(HttpServletResponse.class);
        List<Skill> skillList = controller.skillAddSorting(response, fieldName);
        assertEquals(skills, skillList);
    }

    @Test
    public void addNewSkillWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);
        String url = controller.addNewSkill(new Skill("C++"), result);
        assertEquals(url, "redirect:/skill/add");
    }

    @Test
    public void addNewSkillWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.addNewSkill(new Skill("C++"), result);
        assertEquals(url, "redirect:/skill/view/1");
    }

    @Test
    public void deleteSkill() {
        BindingResult result = mock(BindingResult.class);
        String url = controller.deleteSkill("JS");
        assertEquals(url, "redirect:/skill/view/1");
    }

    @Test
    public void updateSkillWithErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(true);
        String url = controller.updateSkill(new Skill("C++"), result, "Spring");
        assertEquals(url, "redirect:/skill/updateSkill/C++");
    }

    @Test
    public void updateSkillWithoutErrors() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors())
                .thenReturn(false);
        String url = controller.updateSkill(new Skill("C++"), result, "Spring");
        assertEquals(url, "redirect:/skill/view/1");
    }

    @Test
    public void skillSetFilter() {
        String url = controller.skillSetFilter(new SkillFilter());
        assertEquals(url, "redirect:/skill/view/1");
    }

}