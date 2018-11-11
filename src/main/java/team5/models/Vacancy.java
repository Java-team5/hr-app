package team5.models;

import java.util.Date;

public class Vacancy {
    private long id;
    private long idDeveloper;
    private String position;
    private double salaryFrom;
    private double salaryTo;
    private String vacancyState;
    private double experienceYearsRequire;

    public Vacancy() {}

    public Vacancy(final long id, final long idDeveloper,
                   final String position, final double salaryFrom,
                   final double salaryTo, final String vacancyState,
                   final double experienceYearsRequire) {
        this.id = id;
        this.idDeveloper = idDeveloper;
        this.position = position;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.vacancyState = vacancyState;
        this.experienceYearsRequire = experienceYearsRequire;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(final long idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public double getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(final double salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public double getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(final double salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getVacancyState() {
        return vacancyState;
    }

    public void setVacancyState(final String vacancyState) {
        this.vacancyState = vacancyState;
    }

    public double getExperienceYearsRequire() {
        return experienceYearsRequire;
    }

    public void setExperienceYearsRequire(final double experienceYearsRequire) {
        this.experienceYearsRequire = experienceYearsRequire;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) obj;

        if (vacancy.id == this.id
            && this.position.equals(vacancy.position)
            && this.vacancyState.equals(vacancy.vacancyState)
            && Double.toString(this.salaryFrom).
                equals(Double.toString(vacancy.salaryFrom))
            && Double.toString(this.salaryTo).
                equals(Double.toString(vacancy.salaryTo))) {
            return true;
        }
        return false;
    }
}
