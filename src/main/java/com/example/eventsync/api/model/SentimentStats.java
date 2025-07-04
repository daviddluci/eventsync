package com.example.eventsync.api.model;

import jakarta.persistence.Embeddable;


/**
 * Represents sentiment statistics for feedback associated with an event.
 * Tracks counts for positive, neutral, negative, and unrecognized feedback entries.
 */
@Embeddable
public class SentimentStats {
    private Long positiveCount;
    private Long neutralCount; 
    private Long negativeCount; 
    private Long unrecognizedCount;

    
    /**
     * Constructs a new SentimentStats instance with all counts initialized to zero.
     */
    public SentimentStats () {
        positiveCount = 0L;
        neutralCount = 0L;
        negativeCount = 0L;
        unrecognizedCount = 0L;
    }

    public void incrementPositive() {
        this.positiveCount++;
    }      

    public void incrementNeutral() {
        this.neutralCount++;
    }

    public void incrementNegative() {
        this.negativeCount++;
    }

    public void incrementUnrecognized(){
        this.unrecognizedCount++;
    }
    
    public Long getPositiveCount() {
        return positiveCount;
    }

    public void setPositiveCount(Long positiveCount) {
        this.positiveCount = positiveCount;
    }

    public Long getNeutralCount() {
        return neutralCount;
    }

    public void setNeutralCount(Long neutralCount) {
        this.neutralCount = neutralCount;
    }

    public Long getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(Long negativeCount) {
        this.negativeCount = negativeCount;
    }

    public Long getUnrecognizedCount() {
        return unrecognizedCount;
    }

    public void setUnrecognizedCount(Long unrecognizedCount) {
        this.unrecognizedCount = unrecognizedCount;
    }
}
