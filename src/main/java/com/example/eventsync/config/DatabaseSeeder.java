package com.example.eventsync.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.eventsync.api.model.Event;
import com.example.eventsync.repository.EventRepository;
import com.example.eventsync.service.EventService;

import jakarta.transaction.Transactional;


/**
 * Seeds the database with initial event data and sample feedback
 * after the application is ready.
 */
@Component
public class DatabaseSeeder {
    private final EventRepository eventRepository;
    private final EventService eventService;

    public DatabaseSeeder(EventRepository eventRepository, EventService eventService) {
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }


    /**
     * Seeds the database with a set of predefined events and feedbacks
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void seedEvents() {
        if (eventRepository.count() == 0) {
            eventRepository.save(new Event(
                "Culture Night 2025",
                "On June 13, the annual culture and arts festival Culture Night 2025 will once again encourage Vilnius to stay awake. This year's festival slogan - “Browse the Darkness” - invites residents and visitors of the city to explore nighttime Vilnius like a catalogue of events and choose what interests them the most"
                ));
            eventRepository.save(new Event(
                "“The Adventure of Vilnius” Scavenger Hunt",
                "We invite you to recall the city's legends and historical facts to complete game tasks, find specific objects, answer questions, and visit selected locations throughout Vilnius."));
            eventRepository.save(new Event(
                "Midsummer Magic Stories: Free Educational Experiences at Vilnius Tourist Information Centre",
                "On the night of June 23rd to 24th, Lithuania celebrates the mystery of the most magical night and uncovers the ancient roots of Midsummer - Joninės. Join our free educational sessions in June at the Vilnius Tourist Information Centre and discover how this enchanting tradition connects nature, fire, and legends."
                ));
            eventRepository.save(new Event(
                "European Archaeology Days at the Vilnius Defensive Wall Bastion",
                "We invite you to visit a unique site of Vilnius' defensive heritage - the Vilnius Bastion. During the tour, we will travel back in time, exploring archaeological finds from the Stone, Bronze, and Iron Ages that tell us how the Lithuanian ancestors defended their land from the earliest times, what interesting facts they reveal, and what stories the oldest weapons of our ancestors tell. All this and more awaits you in the educational tour we've prepared."
                ));
            eventRepository.save(new Event(
                "MO Yard. Concert",
                "The fourth season of the MO Yard open-air concert series is here, bringing even more fresh and familiar names to the museum terrace for evenings of musical exploration and discovery."
                ));
        }
        eventRepository.flush();

        eventService.submitFeedback(1L, "It was an awesome night!");
        eventService.submitFeedback(1L, "The music was too loud...");
        eventService.submitFeedback(1L, "Amazing! Looking forward next year..");

        eventService.submitFeedback(2L, "Very interesting city.");
        eventService.submitFeedback(2L, "A little bit too expensive.");
        eventService.submitFeedback(2L, "Extraordinary!");

        eventService.submitFeedback(3L, "A bit boring.");
        eventService.submitFeedback(3L, "The music was too loud...");
        eventService.submitFeedback(3L, "Amazing! Looking forward next year..");

        eventService.submitFeedback(4L, "I did not like it");
        eventService.submitFeedback(4L, "The event was as described");
        eventService.submitFeedback(4L, "Very interesting history");

        eventService.submitFeedback(5L, "Concert started on time.");
        eventService.submitFeedback(5L, "The music was so goooooood");
        eventService.submitFeedback(5L, "The artists we're so cool!");

    }
}
