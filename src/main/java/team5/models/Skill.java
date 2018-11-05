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

    /**
     * @return skill.
     */
    public String getSkill() {
        return skill;
    }

    /**
     * @param skill new skill value.
     */
    public void setSkill(final String skill) {
        this.skill = skill;
    }

    /**
     * Constructor.
     */
    public Skill() {
    }

    /**
     * Constructor.
     * @param skill Skill value.
     */
    public Skill(final String skill) {
        this.skill = skill;
    }
}
