package org.pattern;

public class InsufficientRightsException extends Throwable {
    public InsufficientRightsException(User user, String accessedPath) {
        super("The user " + user.getName() + " does not have an access to " + accessedPath);
    }
}
