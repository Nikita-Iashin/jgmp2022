package org.pattern;

public class Main {
    public static void main(String[] args) {

        String userName = args[0];
        String resource = args[1];

        User user = new User(userName);
        SessionManager sessionManager = new SessionManager();
        try {
            sessionManager.createSession(user, resource);
        } catch (InsufficientRightsException e) {
            throw new RuntimeException(userName + "!".repeat(100) + "/n" + resource + "!".repeat(100));
        }
    }

}
