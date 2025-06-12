package com.example.eventsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.eventsync.api.model.Event;
import com.example.eventsync.repository.EventRepository;

import jakarta.annotation.PostConstruct;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @PostConstruct
    public void seedEvents() {
        if (eventRepository.count() == 0) {
            eventRepository.save(new Event("hackathon", "event for programmers"));
            eventRepository.save(new Event("marathon", "event for athletes"));
            eventRepository.save(new Event("shopping", "event for wives"));
            eventRepository.save(new Event("weightlifting", "event for powerlifters"));
            eventRepository.save(new Event("painting", "event for artists"));
        }
    }

    public Optional<Event> getEvent(Long id){
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public void addEvent(Event event){
        eventRepository.save(event);
    }
}
