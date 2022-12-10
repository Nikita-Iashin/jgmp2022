package org.pattern;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InsufficientRightsException {

        List<String> strings = parseInput(readUserInput());
        String userName = strings.get(0);
        String resource = strings.get(1);

        User user = new User(userName);
        SessionManager sessionManager = new SessionManager();
        sessionManager.createSession(user, resource);
    }

    private static List<String> readUserInput() {
        return List.of(new Scanner(System.in).nextLine());
    }

    private static List<String> parseInput(List<String> input) {
        return List.of(input.get(0).split("\\s+"));
    }


}
