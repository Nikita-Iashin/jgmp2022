package org;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.pattern.SessionManager;
import org.pattern.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccessManagementTest {

    @Test
    void userNameIsNotNullOrBlankTest() {
        String userName = "";
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new User(userName),
                "User Name should be not null and should be not blank"
        );
        assertTrue(thrown.getMessage().contentEquals("An Empty name provided for the user"));

    }

    @SneakyThrows
    @Test
    void happyPathTest() {
        String userName = "admin";
        User user = new User(userName);
        String resource = "/admin";
        boolean session = new SessionManager().createSession(user, resource);

        assertTrue(session);
    }


}
