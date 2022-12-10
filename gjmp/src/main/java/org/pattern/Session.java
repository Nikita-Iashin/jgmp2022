package org.pattern;

public class Session {
    public Session(User user) {
        System.out.println("Session for user: " + user.getName() + " created.");
    }
}
