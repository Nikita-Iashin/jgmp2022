package org.pattern;

public class SessionManager {
    private AccessChecker access = AccessChecker.getInstance();

    public boolean createSession(User user, String accessedPath) throws InsufficientRightsException {
        if (access.mayAccess(user, accessedPath)) {
            new Session(user);
            return true;
        } else {
            throw new InsufficientRightsException(user, accessedPath);
        }
    }
}
