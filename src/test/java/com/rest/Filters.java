package com.rest;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

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
     * Example test showing how to log request and response details
     * using built-in filters: RequestLoggingFilter and ResponseLoggingFilter.
     */
    @Test
    public void loggingFilterExample() {
        given()
                .baseUri("https://postman-echo.com")
                // Apply filters to log request and response details
                .filter(new RequestLoggingFilter(LogDetail.BODY))   // Logs request URI, method, headers, etc.
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))  // Logs response status, headers, and body
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200);  // Verify the response status is 200 OK
    }
}