package com.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.File;
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

    @Test
    public void multipart_form_data_example() {
        given()
                .baseUri(BASE_URI)
                .multiPart("foo1", "bar1")
                .multiPart("foo2", "bar2")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))// Validate the form data in the response
                .body("form.foo2", equalTo("bar2")); // Validate the form data in the response
    }

    @Test
    public  void uploadFile_multiple_form_data(){
        String attributes = "{\"name\" : \"temp.txt\" , \"parent\" : {\"id\" : \"1234\"}}";
            given().
                    baseUri(BASE_URI).
                    log().all().
                    multiPart("file", new File("temp.txt")).
                    multiPart("attributes" ,attributes,"application/json" ). // Uploading a file with content
                    when().
                    post("/post").
                    then().
                    log().all().
                    assertThat().
                    statusCode(200);

    }
}