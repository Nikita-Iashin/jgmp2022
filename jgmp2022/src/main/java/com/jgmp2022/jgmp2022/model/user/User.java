package com.jgmp2022.jgmp2022.model.user;

import com.jgmp2022.jgmp2022.model.event.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String name;
    private List<Event> events;

    public User(String testUser) {
        this.name = testUser;
        this.events = new ArrayList<>();
    }
}
