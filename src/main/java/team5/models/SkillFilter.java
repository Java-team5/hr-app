package team5.models;

/**
 * Filter for entity 'Skill'.
 */
public class SkillFilter {
    /**
     * Filter.
     */
    private String skill;

    /**
     * @return filter.
     */
    public String getSkill() {
        return skill;
    }

    /**
     * @param skill filter
     */
    public void setSkill(final String skill) {
        this.skill = skill;
    }

    /**
     * Constructor.
     * @param skill value.
     */
    public SkillFilter(final String skill) {
        this.skill = skill;
    }

    /**
     * Constructor.
     */
    public SkillFilter() {
        this.skill = "";
    }
}
