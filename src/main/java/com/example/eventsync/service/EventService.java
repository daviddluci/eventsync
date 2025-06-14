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


/**
 * Service class handling event logic including sentiment analysis
 * via the huggingfaceAPI.
 */
@Service
public class EventService {
    private final EventRepository eventRepository;

    @Value("${huggingface.api.key}")
    private String huggingfaceApiKey;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }


    /**
     * Adds user feedback to the event specified by id, performs
     * sentiment analysis on the feedback text, and updates event feedback with sentiment stats.
     * 
     * @param id ID of event to which feedback should be added
     * @param text text submitted by the user
     * @return true if event exists and feedback is added, false otherwise
     */
    public boolean submitFeedback(Long id, String text) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isEmpty()) {
            return false;
        }

        Event event = eventOpt.get();
        SentimentResponse sentiment = analyzeSentiment(text);
        event.addFeedback(text, sentiment.getLabel());

        eventRepository.save(event);
        return true;
    }


    /**
     * Calls huggingfaceAPI to analyze sentiment of the given text
     * Returns a SentimentResponse instance containing sentiment labels and scores.
     * 
     * @param text text to analyze
     * @return SentimentResponse with the detected sentiment label or empty result on error
     */
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


    /**
     * Saves a new event to the repository.
     * @param event
     */
    public void addEvent(Event event){
        eventRepository.save(event);
    }

    public Optional<Event> getEvent(Long id){
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
}
