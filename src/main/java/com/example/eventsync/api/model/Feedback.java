package com.example.eventsync.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

/**
 * Represents a single feedback entry associated with an event
 * Contains the feedback text, sentiment classification and the submission timestamp.
 */
@Embeddable
public class Feedback {
    private String text;
    private String sentiment;
    private LocalDateTime submittedAt;

    protected Feedback() {}


    /**
     * Constructs a Feedback specified with text and sentiment classification
     * Submission time is determined automatically to the current time
     * 
     * @param text
     * @param sentiment
     */
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
