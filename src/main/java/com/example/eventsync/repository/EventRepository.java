package com.example.eventsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eventsync.api.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
