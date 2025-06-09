package com.rest;

import com.resreq.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

/**
 * This class demonstrates how to perform a PUT request using Rest Assured
 * to update a Postman workspace and validate the response using BDD style assertions.
 */
public class AutomatePut {

    // Reusable response specification
    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        // Load base URI and API key from config file
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");

        // Setup request specification
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("X-Api-Key", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        // Setup response specification
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Test case: Validate PUT request using BDD style.
     * Updates an existing workspace and validates the response.
     */
    @Test
    public void validatePutRequestBDDStyle() {
        // Workspace ID to be updated
        String workspaceId = "aa243405-bcf4-47f4-a33f-21f58a5052cb";

        // Payload for updating the workspace
        String payload = "{\n" +
                "  \"workspace\": {\n" +
                "    \"name\": \"MyUpdatedWorkSpace4\",\n" +
                "    \"type\": \"personal\",\n" +
                "    \"description\": \"This workspace was updated via PUT request\",\n" +
                "    \"visibility\": \"personal\"\n" +
                "  }\n" +
                "}";

        // PUT request with path param and validations
        given()
                .pathParam("workspaceId", workspaceId)
                .body(payload)
                .when()
                .put("/workspaces/{workspaceId}")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("workspace.name", equalTo("MyUpdatedWorkSpace4"))
                .body("workspace.id", matchesPattern("^[a-z0-9\\-]+$"))
                .body("workspace.id", equalTo(workspaceId));
    }
}