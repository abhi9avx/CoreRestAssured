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

/**
 * This class demonstrates how to automate a DELETE request using Rest Assured
 * to remove a workspace from Postman and verify the response.
 */
public class AutomateDelete {

    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        // Load base URL and API key from config properties
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");

        // Setup common request specification for all API calls
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("X-Api-Key", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        // Setup common expected response specification
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Test to validate DELETE operation for a workspace by ID.
     */
    @Test
    public void validateDeleteRequest() {
        String workspaceId = "66a4ac7b-58ef-4137-9b98-80f98b3b47cf"; // Replace with valid ID

        given()
                .pathParam("workspaceId", workspaceId)
                .when()
                .delete("/workspaces/{workspaceId}")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("workspace.id", equalTo(workspaceId));
    }
}