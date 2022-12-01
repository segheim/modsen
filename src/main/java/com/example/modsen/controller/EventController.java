package com.example.modsen.controller;

import com.example.modsen.exception.EmptyResultControllerException;
import com.example.modsen.exception.RequestEntityControllerException;
import com.example.modsen.model.Event;
import com.example.modsen.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody Event event) {
        return eventService.add(event).orElseThrow(() -> new RequestEntityControllerException("Event is exist!"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Event> findAll() {
        final List<Event> events = eventService.searchAll();
        if (events.isEmpty()) {
            throw new EmptyResultControllerException("Events are not exist!");
        }
        return events;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event findById(@PathVariable Long id) {
        return eventService.searchById(id).orElseThrow(() -> new EmptyResultControllerException("Event is not exist with this id!"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event update(@PathVariable Long id, @RequestBody Event event) {
        return eventService.patch(id, event).orElseThrow(() -> new EmptyResultControllerException("Event is not exist for update!"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event remove(@PathVariable Long id) {
        return eventService.delete(id).orElseThrow(() -> new EmptyResultControllerException("Event can't delete with this id"));
    }

}
