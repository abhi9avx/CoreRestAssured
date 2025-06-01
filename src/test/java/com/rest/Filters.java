package com.rest;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

/**
 * This test class demonstrates the usage of Rest Assured filters
 * to log HTTP request and response details for debugging and reporting.
 *
 * Filters are reusable components that can be applied to log, modify,
 * or inspect HTTP requests/responses in a clean and modular way.
 */
public class Filters {

    /**
     * Example test showing how to log specific request and response parts
     * using built-in filters: RequestLoggingFilter and ResponseLoggingFilter.
     */
    @Test
    public void loggingFilterExample() {
        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.BODY))       // Logs request body only
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))    // Logs response status only
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200);  // Verify the response status is 200 OK
    }

    /**
     * Example test that logs both request and response details to a file
     * named 'restassured.log' using PrintStream.
     */
    @Test
    public void fileLoggingFilterExample() throws FileNotFoundException {
        // Create a PrintStream pointing to a log file
        PrintStream fileOutputStream = new PrintStream(new File("restassured.log"));

        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(fileOutputStream))      // Logs full request to file
                .filter(new ResponseLoggingFilter(fileOutputStream))     // Logs full response to file
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200);  // Verify the response status is 200 OK
    }
}