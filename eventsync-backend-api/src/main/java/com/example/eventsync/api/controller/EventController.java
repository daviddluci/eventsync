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


    /**
     * Adds a new event.
     * 
     * @param event the event to add
     * @return ResponsiveEntity with HTTP status code 200(OK)
     */
    @PostMapping("/events")
    public ResponseEntity<Void> addEvent(@RequestBody Event event) {        
        eventService.addEvent(event);
        return ResponseEntity.ok().build();
    }


    /**
     * Returns JSON with all events.
     * 
     * @return
     */
    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }


    /**
     * Submit a feedback to specific event identified by its id.
     * 
     * @param id ID of an event for which feedback is submitted
     * @param feedbackRequest the feedback details from the request body
     * @return ResponsiveEntity with HTTP status code 200(OK) if event and feedback is submitted successfully, or 404 (Not Found) otherwise.
     */
    @PostMapping("/events/{id}/feedback")
    public ResponseEntity<Void> submitFeedback(@PathVariable Long id, @RequestBody FeedbackRequest feedbackRequest) {
        boolean success = eventService.submitFeedback(id, feedbackRequest.getText());
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Returns a summary of an event identified by its ID.
     * The summary includes sentiment breakdown and feedback details.
     * 
     * @param id ID of an event for summary is requested
     * @return ResponseEntity containing the event summary as JSON with HTTP status 200 (OK),
     *         or HTTP status 404 (Not Found) if the event does not exist
     */
    @GetMapping("/events/{id}/summary")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return eventService.getEvent(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
