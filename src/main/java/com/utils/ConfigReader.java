package com.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static String getValue(String key) {
        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream("config.properties");
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Unable to load config.properties file.");
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
