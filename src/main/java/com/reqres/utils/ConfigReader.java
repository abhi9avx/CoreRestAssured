package com.resreq.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static String getValue(String key) {
        Properties prop = new Properties();

        try {
            InputStream fis = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (fis != null) {
                prop.load(fis);
            } else {
                System.out.println("Sorry, unable to find config.properties in the classpath.");
            }
        } catch (IOException e) {
            System.out.println("Error loading config.properties file.");
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
} 