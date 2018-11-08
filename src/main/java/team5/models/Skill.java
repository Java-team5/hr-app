package team5.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Skill {

    @NotNull
    @Size(min = 1, max = 50)
    private String skill;

    public String getSkill() {
        return skill;
    }

    public void setSkill(final String skill) {
        this.skill = skill;
    }

    public Skill() {
    }

    public Skill(final String skill) {
        this.skill = skill;
    }
}
