package com.example.eventsync.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventsync.api.dto.FeedbackRequest;
import com.example.eventsync.api.model.Event;
import com.example.eventsync.service.EventService;



@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController (EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public Event addEvent(@RequestBody Event event) {        
        eventService.addEvent(event);
        return event;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/events/{id}/feedback")
    public ResponseEntity<Event> submitFeedback(@PathVariable Long id,@RequestBody FeedbackRequest feedbackRequest) {
        eventService.submitFeedback(id, feedbackRequest.getText());
        return eventService.getEvent(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    @GetMapping("/events/{id}/summary")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return eventService.getEvent(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    
}
