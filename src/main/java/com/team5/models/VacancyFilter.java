package com.team5.models;

public class VacancyFilter {
    private long idDeveloper;
    private String position;
    private double salaryFrom;
    private double salaryTo;
    private double experienceYearsRequire;

    public VacancyFilter() {
        this.idDeveloper = -1;
        this.position = "";
        this.salaryFrom = 0;
        this.salaryTo = Double.MAX_VALUE;
        this.experienceYearsRequire = Double.MIN_VALUE;
    }

    public VacancyFilter(long idDeveloper, String position, double salaryFrom,
                         double salaryTo, double experienceYearsRequire) {
        this.idDeveloper = idDeveloper;
        this.position = position;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.experienceYearsRequire = experienceYearsRequire;
    }

    public long getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(long idDeveloper) {
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

    public double getExperienceYearsRequire() {
        return experienceYearsRequire;
    }

    public void setExperienceYearsRequire(double experienceYearsRequire) {
        this.experienceYearsRequire = experienceYearsRequire;
    }
}
