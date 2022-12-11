package org.pattern;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ServerConfig {
    private static ServerConfig instance;
    private static String configFilePath = "exm.txt";
    private static Map<String, String> userRights;

    private ServerConfig() {
        userRights = readFile(configFilePath);
    }

    public String getAccessLevel(User user) {
        return userRights.get(user.getName());
    }


    public static ServerConfig getInstance() {
        if (instance == null) {
            return new ServerConfig();
        }
        return instance;
    }

    @SneakyThrows
    private Map<String, String> readFile(String file) {
        Map<String, String> map = new HashMap<>();
        InputStream in = ServerConfig.class.getClassLoader().getResourceAsStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("read line");
            String[] keyValue = line.split("\\s+");
            System.out.println("Key: " + keyValue[0] + " and Value: " + keyValue[1]);
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
}
