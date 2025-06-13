package com.example.eventsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.eventsync.api.dto.SentimentResponse;
import com.example.eventsync.api.dto.SentimentScore;
import com.example.eventsync.api.model.Event;
import com.example.eventsync.repository.EventRepository;

import jakarta.annotation.PostConstruct;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Value("${huggingface.api.key}")
    private String huggingfaceApiKey;

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

    public void submitFeedback(Long id, String text){
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        
        event.addFeedback(text);
        eventRepository.save(event);
        SentimentResponse sentiment = analyzeSentiment(text);
        for (List<SentimentScore> l : sentiment.getScores()){
            for (SentimentScore s : l){
                System.out.println(s.getLabel() + " " + s.getScore());
            }
        }
    }

    private SentimentResponse analyzeSentiment(String text) {
        String url = "https://router.huggingface.co/hf-inference/models/cardiffnlp/twitter-roberta-base-sentiment";

        WebClient.Builder builder = WebClient.builder();

        String requestBody = "{ \"inputs\": \"" + text.replace("\"", "\\\"") + "\" }";

        List<List<SentimentScore>> sentimentScores = builder.build()
            .post()
            .uri(url)
            .header("Authorization", "Bearer " + huggingfaceApiKey)
            .header("Content-Type", "application/json")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<List<SentimentScore>>>() {})
            .block();

        SentimentResponse sentiment = new SentimentResponse(sentimentScores);

        return sentiment;
    }
}
