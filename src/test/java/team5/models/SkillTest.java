package team5.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SkillTest {
    @Test
    public void getSetTest() {
        Skill skill = new Skill();
        skill.setSkill("Java");
        assertEquals("Java", skill.getSkill());
        Skill newSkill = new Skill("C#");
        assertEquals("C#", newSkill.getSkill());
    }
}