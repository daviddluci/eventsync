package com.example.eventsync.api.dto;

public class FeedbackRequest {
    private String text;

    public FeedbackRequest() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
