package com.reqres.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.IOException;

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

        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }
} 