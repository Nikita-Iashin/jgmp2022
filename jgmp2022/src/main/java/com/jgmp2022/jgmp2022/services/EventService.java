package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService {
    public boolean registerUser(User user, Event event) {
        log.info("Registering user {} to the event {}", user.getName(), event.getName());
        return true;
    }
}
