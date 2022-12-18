package com.jgmp2022.jgmp2022.dao;

import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.ticket.Ticket;
import com.jgmp2022.jgmp2022.model.user.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;


@Getter
@Slf4j
@Component
public class Storage {

    private List<Event> events;
    private List<User> users;
    private List<Ticket> tickets;

    @Value("")
    private String eventsFilePath;

    @Value("")
    private String ticketsFilePath;

    @Value("${user.path}")
    private String userFilePath;

    @PostConstruct
    public void loadData() {

    }

    public String readData() {

        log.info(userFilePath + "!".repeat(100));

        return userFilePath;
    }
}
