package org.pattern;

public class AccessChecker {

    private static AccessChecker instance;

    private AccessChecker() {
    }

    public static AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    public boolean mayAccess(User user, String accessedAvailable) {
        String access =ServerConfig.getInstance().getAccessLevel(user);
        if (access != null) {
            return access.equalsIgnoreCase(accessedAvailable);
        }else {
                throw new NullPointerException("Not complete User/Access pair");
        }
    }
}