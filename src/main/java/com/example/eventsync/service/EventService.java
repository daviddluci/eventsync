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
            eventRepository.save(new Event(1L, "hackathon", "event for programmers"));
            eventRepository.save(new Event(2L, "marathon", "event for athletes"));
            eventRepository.save(new Event(3L, "shopping", "event for wives"));
            eventRepository.save(new Event(4L, "weightlifting", "event for powerlifters"));
            eventRepository.save(new Event(5L, "painting", "event for artists"));
        }
    }

    public Optional<Event> getEvent(Long id){
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
}
