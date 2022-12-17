package com.jgmp2022.jgmp2022.model.event;

import com.jgmp2022.jgmp2022.model.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class Event {
    private String name;
    private List<User> participants;

    public Event(String testEvent) {
        this.name = testEvent;
    }
}
