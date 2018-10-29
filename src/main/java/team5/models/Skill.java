package team5.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Skill {

    private long id;

    @NotNull
    @Size(min=1,max=15)
    private String skill;

    public Skill() {
    }

    public Skill(long id, String skill) {
        this.id = id;
        this.skill = skill;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Skill(String skill) {
        this.skill = skill;
    }
}
