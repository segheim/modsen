package com.example.modsen.service.impl;

import com.example.modsen.exception.IncorrectDataRequestServiceException;
import com.example.modsen.model.Event;
import com.example.modsen.repository.EventRepository;
import com.example.modsen.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> add(Event event) {
        return eventRepository.create(event);
    }

    @Override
    public List<Event> searchAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> searchById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Event> patch(Long id, Event event) {
        final Optional<Event> optionalEventFromDB = eventRepository.findById(id);
        if (optionalEventFromDB.isPresent()) {
            Event eventFromBD = optionalEventFromDB.get();
            if (event.getTopic() != null) {
                eventFromBD.setTopic(event.getTopic());
            }
            if (event.getDescription() != null) {
                eventFromBD.setDescription(event.getDescription());
            }
            if (event.getOrganizer() != null) {
                eventFromBD.setOrganizer(event.getOrganizer());
            }
            if (event.getTime() != null) {
                eventFromBD.setTime(event.getTime());
            }
            if (event.getLocation() != null) {
                eventFromBD.setLocation(event.getLocation());
            }
            eventRepository.update(eventFromBD);
            return eventRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Event> delete(Long id) {
        final Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            eventRepository.remove(optionalEvent.get());
            return optionalEvent;
        }
        return Optional.empty();
    }
}
