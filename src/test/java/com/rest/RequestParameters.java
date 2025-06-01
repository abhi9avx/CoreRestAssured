package com.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * This test class demonstrates how to send GET requests
 * with single and multiple query parameters using Rest Assured.
 */
public class RequestParameters {

    private static final String BASE_URI = "https://postman-echo.com";

    /**
     * Test sending a GET request with a single query parameter and validating the response.
     */
    @Test
    public void testSingleQueryParameter() {
        given()
                .baseUri(BASE_URI)
                .queryParam("foo2", "bar2")
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("args.foo2", equalTo("bar2"));
    }

    /**
     * Test sending a GET request with multiple query parameters using a map and validating the response.
     */
    @Test
    public void testMultipleQueryParameters() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("foo5", "bar5");
        queryParams.put("foo6", "bar6");

        given()
                .baseUri(BASE_URI)
                .queryParams(queryParams)
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("args.foo5", equalTo("bar5"))
                .body("args.foo6", equalTo("bar6"));
    }

    @Test
    public void testMultipleValueQueryParameters() {
        given()
                .baseUri(BASE_URI)
                .queryParam("foo", "bar1"," bar2", "bar3")

                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testPathParameter(){
        given()
                .baseUri("https://reqres.in")
                .pathParam("id", 2)
                .log().all()
                .when()
                .get("/api/users/{id}")  // Using path parameter to specify user ID
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
}