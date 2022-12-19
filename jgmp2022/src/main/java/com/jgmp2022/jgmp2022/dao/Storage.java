package com.jgmp2022.jgmp2022.dao;

import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.ticket.Ticket;
import com.jgmp2022.jgmp2022.model.user.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Slf4j
@Component
public class Storage {

    private List<Event> events;
    private List<User> users;
    private List<Ticket> tickets;

    @Value("${events.path}")
    private String eventsFilePath;

    @Value("${tickets.path}")
    private String ticketsFilePath;

    @Value("${users.path}")
    private String userFilePath;

    @PostConstruct
    private void loadData() {
        users = readData(userFilePath).stream().map(User::new).collect(Collectors.toList());
        events = readData(eventsFilePath).stream().map(Event::new).collect(Collectors.toList());
        tickets = readData(ticketsFilePath).stream().map(Ticket::new).collect(Collectors.toList());
    }

    @SneakyThrows
    private List<String> readData(String path) {
        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        }
        return records;
    }
}