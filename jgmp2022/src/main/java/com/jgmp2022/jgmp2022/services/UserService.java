package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.model.Event;
import com.jgmp2022.jgmp2022.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public boolean addTicketToBasket(User user, Event event) {
        log.info("Adding ticket for event {} to the user {} basket", event.getName(), user.getName());
        return user.getEvents().add(event);
    }
}
