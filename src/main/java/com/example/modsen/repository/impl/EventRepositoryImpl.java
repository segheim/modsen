package com.example.modsen.repository.impl;

import com.example.modsen.model.Event;
import com.example.modsen.repository.EventRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EventRepositoryImpl extends AbstractRepository<Event> implements EventRepository {

    protected EventRepositoryImpl() {
        super(Event.class);
    }
}
