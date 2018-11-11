package team5.models;

public class VacancyFilter {
    private String position;

    public VacancyFilter() {
        this.position = "";
    }

    public VacancyFilter(final String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

}
