package org.pattern;

public class AccessChecker {

    private AccessChecker() {
    }

    private static AccessChecker instance;

    public static AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    public boolean mayAccess(User user, String accessedPath) {
        //todo
        return false;
    }
}