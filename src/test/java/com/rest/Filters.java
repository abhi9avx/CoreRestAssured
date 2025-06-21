package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * This test class demonstrates basic REST Assured API testing
 * with clean execution (no console logging).
 */
public class Filters {

    /**
     * Example test demonstrating a clean API call without any logging.
     */
    @Test
    public void loggingFilterExample() {
        given()
                .baseUri("https://postman-echo.com")
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200);  // Verify the response status is 200 OK
    }

    /**
     * Example test demonstrating a silent API call with no file operations.
     */
    @Test
    public void fileLoggingFilterExample() {
        given()
                .baseUri("https://postman-echo.com")
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200);  // Verify the response status is 200 OK
    }
}