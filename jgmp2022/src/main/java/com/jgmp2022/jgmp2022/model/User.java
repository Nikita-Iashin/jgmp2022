package com.jgmp2022.jgmp2022.model;

import com.jgmp2022.jgmp2022.model.event.Event;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    private String name;
    private List<Event> events;

    public User(String testUser) {
        this.name = testUser;
        this.events = new ArrayList<>();
    }
}
