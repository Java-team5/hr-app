package team5.utils;

public enum ActionResult {
    SUCCESS("Success"), ERROR("Error");

    private String type;
    ActionResult(String typeValue) {
        this.type = typeValue;
    }

    @Override
    public String toString() {
        return type;
    }
}
