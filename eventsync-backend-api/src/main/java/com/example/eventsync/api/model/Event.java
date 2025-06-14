package com.example.eventsync.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Represents an event with title, description, feedback, and sentiment analysis.
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(length = 1000)
    private String description;


    /**
     * The list of feedback entries associated with this event.
     */
    @ElementCollection
    private final List<Feedback> feedbacks;

    /**
     * Represents sentiment statistics for feedback associated with an event.
     * Tracks counts for positive, neutral, negative, and unrecognized feedback entries.
     */
    @Embedded
    private SentimentStats feedbackSentimentStats;


    /**
     * Constructs an Event with a specified title and description.
     * Initializes an empty feedback list and sentiment statistics.
     * 
     * @param title
     * @param description
     */
    public Event(String title, String description){
        this.title = title;
        this.description = description;
        this.feedbackSentimentStats = new SentimentStats();
        feedbacks = new ArrayList<>();
    }


    /**
     * Default constructor for JPA
     * Initializes an empty feedback list and sentiment statistics.
     */
    protected Event(){
        this.feedbacks = new ArrayList<>();
        this.feedbackSentimentStats = new SentimentStats();
    }


    /**
     * Adds a feedback to the event and updates sentiment statistics.
     * 
     * @param text the feedback text
     * @param sentiment the sentiment category ("positive", "neutral", "negative", "unrecognized")
     */
    public void addFeedback(String text, String sentiment){
        switch (sentiment){
            case "positive" -> feedbackSentimentStats.incrementPositive();
            case "neutral" -> feedbackSentimentStats.incrementNeutral();
            case "negative" -> feedbackSentimentStats.incrementNegative();
            case "unrecognized" -> feedbackSentimentStats.incrementUnrecognized();
        }
        feedbacks.add(new Feedback(text, sentiment));
    }


    /**
     * Gets the sentiment statistics for the event feedback
     * 
     * @return the sentiment statistics
     */
    public SentimentStats getFeedbackSentimentStats() {
        return feedbackSentimentStats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Feedback> getFeedbacksCopy(){
        return List.copyOf(feedbacks);
    }

    public void setFeedbackSentimentStats(SentimentStats feedbackSentimentStats) {
        this.feedbackSentimentStats = feedbackSentimentStats;
    }
}
