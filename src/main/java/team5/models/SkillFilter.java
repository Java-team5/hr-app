package team5.models;

public class SkillFilter {

    private String skill;

    public SkillFilter(String skill) {
        this.skill = skill;
    }
    public SkillFilter() {
        this.skill = "";
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
