package team5.models;

public class FeedbackFilter {

    private String state;
    private String reason;

    public FeedbackFilter() {
        this.state = "";
        this.reason = "";
    }

    public FeedbackFilter(String state, String reason) {
        this.state = state;
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
