package com.example.eventsync.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

    @ElementCollection
    private List<Feedback> feedbacks;

    public Event(String title, String description){
        this.title = title;
        this.description = description;
        feedbacks = new ArrayList<>();
    }

    protected Event(){}

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

    public void addFeedback(String text){
        feedbacks.add(new Feedback(text));
    }

    public List<Feedback> getFeedbacksCopy(){
        return List.copyOf(feedbacks);
    }
}
