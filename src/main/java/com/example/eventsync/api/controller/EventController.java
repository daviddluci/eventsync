package com.example.eventsync.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventsync.api.model.Event;
import com.example.eventsync.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController (EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/{id}/summary")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEvent(id).orElse(new Event(0, null, null));
    }

    @PostMapping("/events")
    public Event addEvent(@RequestBody Event event) {        
        eventService.addEvent(event);
        return event;
    }
    
    
}
