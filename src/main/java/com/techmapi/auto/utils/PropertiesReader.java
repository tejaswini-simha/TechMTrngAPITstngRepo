package com.techmapi.auto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesReader {
    private Properties prop = new Properties();

    PropertiesReader() {
        String propertiesFilePath = "config.properties";
        InputStream inputStream;
        inputStream = getInputStream(propertiesFilePath);

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getInputStream(String propertiesFilePath) {
        return this.getClass().getClassLoader().getResourceAsStream(propertiesFilePath);
    }

    String getBaseUri() {
        return prop.getProperty("baseURI");
    }


    String createUser() {
        return getBaseUri()+prop.getProperty("createUser");
    }

    String updateUser() {
        return getBaseUri()+prop.getProperty("updateUser");
    }

    String getUser() {
        return getBaseUri()+prop.getProperty("getUser");
    }
    
    String getUsersOnPageEndPOint() {
        return getBaseUri()+prop.getProperty("getAllUsersOnPage");
    }

    String deleteUser() {
        return getBaseUri()+prop.getProperty("deleteUser");
    }
}
