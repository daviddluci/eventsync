package com.example.eventsync.api.dto;

/**
 * Data transfer object representing a single sentiment label returned by the huggingfaceAPI.
 */
public class SentimentScore {
    private String label;
    private double score;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
