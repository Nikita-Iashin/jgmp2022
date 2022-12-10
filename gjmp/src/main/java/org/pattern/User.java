package org.pattern;

import lombok.Getter;

@Getter
public class User {
    String name;

    public User(String name) {
        this.name = checkName(name);
    }

    private String checkName(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("An Empty name provided for the user");
        }
        return name;
    }
}
