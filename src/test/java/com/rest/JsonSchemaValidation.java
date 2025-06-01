package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * This class demonstrates how to validate a JSON response
 * against a predefined JSON Schema using Rest Assured.
 *
 * Prerequisites:
 * - Place your schema file (e.g., EchoGet.json) inside `src/test/resources`
 * - Add the required Rest Assured + JSON Schema Validator dependencies in your `pom.xml`
 */
public class JsonSchemaValidation {

    private static final String BASE_URI = "https://postman-echo.com";

    /**
     * Validates the response of a GET request to /get
     * against a JSON schema (EchoGet.json) stored in resources.
     */
    @Test
    public void validateJsonSchema() {
        given()
                .baseUri(BASE_URI)
                .log().uri()
                .when()
                .get("/get")
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("EchoGet.json")); // Make sure this is under src/test/resources
    }
}