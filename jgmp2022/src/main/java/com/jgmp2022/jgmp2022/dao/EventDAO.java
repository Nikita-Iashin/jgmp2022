package com.jgmp2022.jgmp2022.dao;

import com.jgmp2022.jgmp2022.model.event.Event;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventDAO {

    @Getter
    private List<Event> events;

    {
        events = new ArrayList<>();
        events.add(new Event("Meet Up Java"));
        events.add(new Event("Meet Up JS"));
        events.add(new Event("Meet Up Python"));
        events.add(new Event("Meet Up C#"));
    }

    public Event getEvent(String event) {
        return events.stream().filter(e -> e.getName().equalsIgnoreCase(event)).findAny().orElse(null);
    }

    public boolean addEvent(String event) {
        return events.add(new Event(event));
    }

    public void deleteEvent(String event) {
        events.stream().filter(e -> e.getName().equalsIgnoreCase(event)).forEach(e -> events.remove(e));
    }

    public void updateEvent(String event) {
        events.stream().filter(e -> e.getName().equalsIgnoreCase(event)).findFirst().ifPresent(e -> e.setName(event));
    }
}
