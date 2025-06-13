package com.example.eventsync.api.dto;

import java.util.List;

public class SentimentResponse {
    private List<List<SentimentScore>> scores;

    public SentimentResponse(List<List<SentimentScore>> scores) {
        this.scores = scores;
    }

    public List<List<SentimentScore>> getScores() {
        return scores;
    }

    public void setScores(List<List<SentimentScore>> scores) {
        this.scores = scores;
    }
}
