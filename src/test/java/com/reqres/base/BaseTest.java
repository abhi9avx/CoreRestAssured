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
        // Set base URI
        RestAssured.baseURI = "https://reqres.in";

        // Load properties for API key
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        properties.load(fis);

        // Create request specification with API key
        requestSpec = new RequestSpecBuilder()
                .addHeader("x-api-key", properties.getProperty("reqres.api.key"))
                .build();
    }
} 