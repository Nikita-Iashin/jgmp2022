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
public class UserService {

    @Autowired
    Storage storage;

    public List<User> getUsers() {
        return storage.getUsers();
    }

    public boolean addTicketToBasket(User user, Event event) {
        log.info("Adding ticket for event {} to the user {} basket", event.getName(), user.getName());
        return user.getEvents().add(event);
    }
}
