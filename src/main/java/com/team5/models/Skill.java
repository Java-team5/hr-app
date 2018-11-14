package com.team5.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "skill")
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
