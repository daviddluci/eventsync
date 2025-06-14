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

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Value("${huggingface.api.key}")
    private String huggingfaceApiKey;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
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
        
        SentimentResponse sentiment = analyzeSentiment(text);
        event.addFeedback(text, sentiment.getLabel());
        
        eventRepository.save(event);
    }

    private SentimentResponse analyzeSentiment(String text) {
        String url = "https://router.huggingface.co/hf-inference/models/cardiffnlp/twitter-roberta-base-sentiment";

        WebClient.Builder builder = WebClient.builder();

        String requestBody = "{ \"inputs\": \"" + text.replace("\"", "\\\"") + "\" }";

        try{
            List<List<SentimentScore>> sentimentScores = builder.build()
                .post()
                .uri(url)
                .header("Authorization", "Bearer " + huggingfaceApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<List<SentimentScore>>>() {})
                .block();

            if (sentimentScores == null || sentimentScores.isEmpty()){
                System.err.println("W: empty huggingface api yield");
                return new SentimentResponse(null);
            }

            return new SentimentResponse(sentimentScores);

        } catch (Exception e){
            System.err.println("E: error calling huggingface api");
            return new SentimentResponse(List.of());
        }
    }
}
