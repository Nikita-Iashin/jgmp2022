package org.pattern;

public class Main {
    public static void main(String[] args) throws InsufficientRightsException {

        //args[0]
        //> test_user /user

        AccessChecker accessChecker = AccessChecker.getInstance();

        User user = new User(args[0]);
        SessionManager sessionManager = new SessionManager();
        sessionManager.createSession(user, "/user");
    }
}
