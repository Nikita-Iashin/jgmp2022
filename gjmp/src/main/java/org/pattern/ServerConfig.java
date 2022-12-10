package org.pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

    private Map<String, String> readFile(String filePath) {
        Map<String, String> map = new HashMap<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileFromResource(filePath)))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split("\\s+");
                map.put(keyValuePair[0], keyValuePair[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
