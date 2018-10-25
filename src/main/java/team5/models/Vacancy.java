package team5.models;

import java.util.Date;

public class Vacancy {
    private int id;
    private int idDeveloper;
    private String position;
    private double salaryFrom;
    private double salaryTo;
    private String vacancyState;
    private double experienceYearsRequire;

    public Vacancy() {
    }

    public Vacancy(int id, int idDeveloper, String position, double salaryFrom, double salaryTo, String vacancyState, double experienceYearsRequire) {
        this.id = id;
        this.idDeveloper = idDeveloper;
        this.position = position;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.vacancyState = vacancyState;
        this.experienceYearsRequire = experienceYearsRequire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(int idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(double salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public double getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(double salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getVacancyState() {
        return vacancyState;
    }

    public void setVacancyState(String vacancyState) {
        this.vacancyState = vacancyState;
    }

    public double getExperienceYearsRequire() {
        return experienceYearsRequire;
    }

    public void setExperienceYearsRequire(double experienceYearsRequire) {
        this.experienceYearsRequire = experienceYearsRequire;
    }
}
