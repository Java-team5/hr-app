package team5.models;

public class Filter {
    private String value;

    public Filter(String value) {
        this.value = value;
    }
    public Filter() {
        this.value = "";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
