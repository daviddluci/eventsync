package com.example.eventsync.api.dto;


/**
 * Data transfer object used to receive feedback text in a JSON request body.
 */
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
