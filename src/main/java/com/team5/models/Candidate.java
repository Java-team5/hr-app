package com.team5.models;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @Column(name = "ID")
    private long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "SURNAME")
    private String surname;

    @Basic
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Basic
    @Column(name = "SALARY")
    private double salary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Candidate(){}

    public Candidate(long id, String name, String surname, Date birthday, double salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.salary = salary;
    }

}
