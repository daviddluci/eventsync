package com.example.eventsync.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class Feedback {
    private String text;
    private String sentiment;
    private LocalDateTime submittedAt;

    protected Feedback() {}

    public Feedback(String text, String sentiment){
        this.text = text;
        this.sentiment = sentiment;
        this.submittedAt = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
