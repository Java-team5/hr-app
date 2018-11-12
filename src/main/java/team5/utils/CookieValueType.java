package team5.utils;

public enum CookieValueType {
    SORT("SORT"), FILTER("FILTER");

    private String type;

    private CookieValueType(final String typeValue) {
        this.type = typeValue;
    }

    @Override
    public String toString() {
        return type;
    }
}
