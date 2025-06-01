package com.rest;

import com.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

/**
 * This class demonstrates how to automate a POST request using both
 * BDD and non-BDD styles with Rest Assured.
 * It validates workspace creation on the Postman API.
 */
public class AutomatePost {

    /**
     * Setup method runs once before all tests.
     * Initializes base URI, headers, and logging using request and response specifications.
     */
    @BeforeClass
    public void setup() {
        // Load config values from properties file
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");

        // Build request specification for all requests
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("X-Api-Key", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        // Build response specification to expect 200 status and JSON content
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    /**
     * Test to validate POST request using BDD Style (Given-When-Then).
     * This style improves readability and works well for test reporting.
     */
    @Test
    public void validatePostRequestBDDStyle() {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"MyFirstWorkSpace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured Created this\",\n" +
                "        \"visibility\": \"personal\"\n" +
                "    }\n" +
                "}";

        // BDD-style POST request with inline validation
        given()
                .body(payload)
                .when()
                .post("/workspaces")
                .then()
                .assertThat()
                .body("workspace.name", equalTo("MyFirstWorkSpace"))
                .body("workspace.id", matchesPattern("^[a-z0-9-]+$")); // Workspace ID pattern validation
    }

    /**
     * Test to validate POST request using Non-BDD Style (imperative approach).
     * This style gives more control over response handling and logic branching.
     */
    @Test
    public void validatePostRequestNonBDDStyle() {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"MyFirstWorkSpace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured Created this\",\n" +
                "        \"visibility\": \"personal\"\n" +
                "    }\n" +
                "}";

        // Non-BDD style: send request and extract response object
        Response response = with()
                .body(payload)
                .post("/workspaces");

        // Extract values and assert manually
        assertThat("Workspace name should match",
                response.path("workspace.name"), equalTo("MyFirstWorkSpace"));

        assertThat("Workspace ID should match expected pattern",
                response.path("workspace.id"), matchesPattern("^[a-z0-9-]+$"));
    }
}