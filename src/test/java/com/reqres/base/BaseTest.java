package com.reqres.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class BaseTest {
    protected RequestSpecification requestSpec;
    protected static final String BASE_URL = System.getProperty("BASE_URL", "https://reqres.in");
    
    // Use environment variable if available and not empty, otherwise use hardcoded fallback
    private static final String API_KEY = getApiKey();
    
    private static String getApiKey() {
        String envApiKey = System.getenv("API_KEY");
        if (envApiKey != null && !envApiKey.trim().isEmpty()) {
            return envApiKey.trim();
        }
        return "reqres-free-v1"; // Fallback to known working key
    }

    @BeforeClass
    public void setup() throws IOException {
        // Set base URI
        RestAssured.baseURI = BASE_URL;

        // Create logs directory if it doesn't exist and we're not in CI
        boolean isCI = System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;
        
        try {
            if (!isCI) {
                File logDir = new File("src/test/resources/logs");
                if (!logDir.exists()) {
                    logDir.mkdirs();
                }
                
                // Local: Log to file only, no console output
                RestAssured.config = RestAssured.config()
                    .logConfig(RestAssured.config().getLogConfig()
                        .defaultStream(new PrintStream("src/test/resources/logs/reqres_test.log")));
            }
            // CI: Completely silent - no HTTP logging at all
            
        } catch (FileNotFoundException e) {
            // Fallback: completely silent execution
        }

        // Create request specification with API key authentication
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("x-api-key", API_KEY)
                .addHeader("Accept", "application/json")
                .build();
    }
} 