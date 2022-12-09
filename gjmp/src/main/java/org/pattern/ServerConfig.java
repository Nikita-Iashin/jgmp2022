package org.pattern;

import java.util.HashMap;
import java.util.Map;

public class ServerConfig {
    private static ServerConfig instance;
    private static String configFilePath = "src/main/java/org/pattern/exm.txt";
    private static Map<String, String> userRights;

    public ServerConfig() {
        //userRights
    }

    public String getAccessLevel(User u) {
        //    ...
        return null;
    }


    public static ServerConfig getInstance() {
        if (instance == null) {
            // create instance
        }
        return instance;
    }
}
