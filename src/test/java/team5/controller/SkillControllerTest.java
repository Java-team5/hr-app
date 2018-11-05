package team5.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import team5.models.Skill;
import team5.service.SkillService;

import javax.servlet.http.Cookie;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SkillControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private final String fieldName = "skill";

    private SkillService service;
    private SkillController controller;

    @Before
    public void SetUp() throws Exception{
        controller = new SkillController();

        service = mock(SkillService.class);
    }

    @Test
    public void skillView() throws Exception {

        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("C#"));
        skills.add(new Skill("Java"));

        when(service.getFilteredEntitiesByPage(fieldName,"", 1, 5))
                .thenReturn(skills);

        List<Skill> skillList = controller.skillView(new Cookie("", "1"), 1);

    }

    @Test
    public void skillViewById() throws Exception {
        when(skillService.getById("Java"))
                .thenReturn(new Skill("Java"));

        mockMvc.perform(get("/skill/viewSkillById/{id}", "Java"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
                //.andExpect(jsonPath("$", hasSize(1)));
                //.andExpect(jsonPath("$skill", is("Java")));
    }
}