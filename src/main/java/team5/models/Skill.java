package team5.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity 'Skill'.
 */
public class Skill {

    /**
     * PK.
     */
    @NotNull
    @Size(min = 1, max = 50)
    private String skill;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Skill() {
    }

    public Skill(String skill) {
        this.skill = skill;
    }
}
