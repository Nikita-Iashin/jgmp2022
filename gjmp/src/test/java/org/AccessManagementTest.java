package org;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.pattern.AccessChecker;
import org.pattern.SessionManager;
import org.pattern.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccessManagementTest {


    AccessChecker accessChecker = AccessChecker.getInstance();
    SessionManager sessionManager = new SessionManager();

    @SneakyThrows
    @Test
    void notCompleteKeyValueTest() {
        String userName = "user";
        String resource = "";

        User user = new User(userName);

        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> sessionManager.createSession(user, resource),
                "Not complete user/access pair should throw a NullPointer exception"
        );

        assertTrue(thrown.getMessage().contentEquals("Not complete User/Access pair"));
    }

    @Test
    void userNameIsNotNullOrBlank() {
        String userName = "";
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new User(userName),
                "User Name should be not null and should be not blank"
        );
        assertTrue(thrown.getMessage().contentEquals("An Empty name provided for the user"));

    }


}
