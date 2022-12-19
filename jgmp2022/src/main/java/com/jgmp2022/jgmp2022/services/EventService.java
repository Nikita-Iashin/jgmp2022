package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.dao.Storage;
import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventService {

    @Autowired
    Storage storage;

    public boolean registerUser(User user, Event event) {
        log.info("Registering user {} to the event {}", user.getName(), event.getName());
        return true;
    }

    public List<Event> getEvents() {
        return storage.getEvents();
    }


}
