package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecificationTest {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private static final String BASE_URL = System.getProperty("BASE_URL", "https://reqres.in");

    @BeforeClass
    public void setup() {
        // Request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();

        // Response specification
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .expectBody("page", equalTo(2))
                .build();
    }

    @Test
    public void testWithResponseSpec() {
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpec);
    }

    @Test
    public void validateStatusCode() {
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpec);
    }

    @Test
    public void validateResponseBody() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpec)
                .extract()
                .response();

        // Verify the first user's email
        String userEmail = response.path("data[0].email");
        assertThat("First user's email should be 'michael.lawson@reqres.in'", 
                  userEmail, 
                  equalTo("michael.lawson@reqres.in"));
    }
}