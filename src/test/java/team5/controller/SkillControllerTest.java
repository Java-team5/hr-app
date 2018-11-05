package team5.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import team5.models.Skill;
import team5.service.SkillService;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class SkillControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Mock
    private SkillService skillService;

    private MockMvc mockMvc;

    @InjectMocks
    private SkillController skillController;

    private final String fieldName = "skill";

    @Before
    public void SetUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(skillController).build();
    }

    @Test
    public void skillView() throws Exception {

        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("C#"));
        skills.add(new Skill("Java"));

        when(skillService.getFilteredEntitiesByPage(fieldName,"", 1, 5))
                .thenReturn(skills);
        mockMvc.perform(get("/skill/view/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
                //.andExpect(jsonPath("$[0].skill", is("C++")));
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

    /*@Test
    public void skillAddSorting() {
    }

    @Test
    public void addNewSkill() {
    }

    @Test
    public void deleteSkill() {
    }

    @Test
    public void updateSkill() {
    }

    @Test
    public void skillSetFilter() {
    }*/
}