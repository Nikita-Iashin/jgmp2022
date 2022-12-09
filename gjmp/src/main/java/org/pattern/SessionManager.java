package org.pattern;

public class SessionManager {
    private AccessChecker access = AccessChecker.getInstance();

    public Session createSession(User user, String accessedPath) throws InsufficientRightsException {
        if (access.mayAccess(user, accessedPath)) {
            return new Session(user);
        } else {
            throw new InsufficientRightsException(user, accessedPath);
        }
    }
}
