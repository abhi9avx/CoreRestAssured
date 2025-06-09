package com.reqres.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setup() throws IOException {
        // Set base URI from system property or use default
        String baseUrl = System.getProperty("BASE_URL", "https://reqres.in");
        RestAssured.baseURI = baseUrl;

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
                    .addHeader("x-api-key", properties.getProperty("reqres.api.key"))
                    .build();
        } catch (IOException e) {
            // If config.properties is not found, create request spec without API key
            requestSpec = new RequestSpecBuilder().build();
        }
    }
} 