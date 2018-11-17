package com.team5.models;

public class Feedback {
    private int idInterview;
    private int idInterviewer;
    private String feedbackState;
    private String reason;

    public Feedback(){}

    public Feedback(int idInterview, int idInterviewer, String feedbackState, String reason) {
        this.idInterview = idInterview;
        this.idInterviewer = idInterviewer;
        this.feedbackState = feedbackState;
        this.reason = reason;
    }


    public int getIdInterview() {
        return idInterview;
    }

    public void setIdInterview(int idInterview) {
        this.idInterview = idInterview;
    }

    public int getIdInterviewer() {
        return idInterviewer;
    }

    public void setIdInterviewer(int idInterviewer) {
        this.idInterviewer = idInterviewer;
    }

    public String getFeedbackState() {
        return feedbackState;
    }

    public void setFeedbackState(String feedbackState) {
        this.feedbackState = feedbackState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
