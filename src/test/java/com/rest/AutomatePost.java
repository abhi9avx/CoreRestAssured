package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.notNullValue;

import com.reqres.base.BaseTest;

/**
 * This class demonstrates how to automate POST requests using Rest Assured.
 * Covers: BDD style, Non-BDD style, and Map-based payload for user creation.
 */
public class AutomatePost extends BaseTest {

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method
    }

    /**
     * BDD-style test to validate user creation using a JSON file.
     */
    @Test
    public void validatePostRequest_BDD_Style() {
        File file = new File("src/main/resources/CreateUserPayload.json"); // Assuming you have this file now

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(file)
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .log().all();
    }

    /**
     * Non-BDD style test to validate user creation using a string payload.
     */
    @Test
    public void validatePostRequest_NonBDD_Style() {
        String payload = "{\n" +
                "    \"name\": \"Neo\",\n" +
                "    \"job\": \"The One\"\n" +
                "}";

        Response response = given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/api/users");

        assertThat("User name should match",
                response.path("name"), equalTo("Neo"));

        assertThat("User job should match",
                response.path("job"), equalTo("The One"));

        assertThat("User ID should not be null",
                response.path("id"), notNullValue());

        assertThat("createdAt should not be null",
                response.path("createdAt"), notNullValue());
        
        response.then().log().all(); // Log response for debugging
    }

    /**
     * BDD-style test to create a user using a HashMap as payload.
     */
    @Test
    public void validatePostRequest_UsingMapPayload() {
        HashMap<String, Object> userDetails = new HashMap<>();

        userDetails.put("name", "Trinity");
        userDetails.put("job", "hacker");

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(userDetails)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("Trinity"))
                .body("job", equalTo("hacker"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    // This test method was already in AutomatePost and is correct for reqres.in
    // I've kept it as is, only ensuring it uses requestSpec implicitly via BaseTest.
    @Test
    public void postMethodBDDStyle() {
        String requestBody = "{\n    \"name\": \"morpheus\",\n    \"job\": \"leader\"\n}";

        given()
                .spec(requestSpec) // Use inherited requestSpec
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .log().all();
    }
}