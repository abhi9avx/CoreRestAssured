package com.reqres.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected RequestSpecification requestSpec;
    protected static final String BASE_URL = System.getProperty("BASE_URL", "https://reqres.in");

    @BeforeClass
    public void setup() throws IOException {
        // Set base URI
        RestAssured.baseURI = BASE_URL;

        // Create logs directory if it doesn't exist
        File logDir = new File("src/test/resources/logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        // Configure RestAssured logging
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssured.config()
            .logConfig(RestAssured.config().getLogConfig()
                .enableLoggingOfRequestAndResponseIfValidationFails()
                .enablePrettyPrinting(true)
                .defaultStream(new java.io.PrintStream("src/test/resources/logs/reqres_test.log")));

        // Load properties for API key
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            // Create request specification with API key
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .addHeader("x-api-key", properties.getProperty("reqres.api.key"))
                    .build();
        } catch (IOException e) {
            // If config.properties is not found, create request spec without API key
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .build();
        }
    }
} 