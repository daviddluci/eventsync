package com.example.eventsync.api.dto;

import java.util.List;

/**
 * Data transfer object used to represent sentiment response structure.
 */
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

    public String getLabel(){
        if (!scores.isEmpty() && !scores.get(0).isEmpty()){
            return switch (scores.get(0).get(0).getLabel()){
                case "LABEL_0" -> "negative";
                case "LABEL_1" -> "neutral";
                case "LABEL_2" -> "positive";
                default -> {
                    yield "unrecognized";
                }
            };
        }
        return "unrecognized";
    }
}
