package com.rest;

import com.reqres.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecificationTest {

    // Declaring reusable response specification
    private ResponseSpecification responseSpec;

    // Base URL and API key will be read from config file
    private String baseUrl;
    private String apiKey;

    @BeforeClass
    public void setup() {
        // Load config values
        baseUrl = ConfigReader.getValue("base.url");
        apiKey = ConfigReader.getValue("postman.api.key");

        // Build the request specification using RestAssured
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(baseUrl) // Set base URI for all requests
                .addHeader("X-API-Key", apiKey) // Add API key as a header
                .setContentType(ContentType.JSON) // Set content type to JSON
                .log(LogDetail.ALL); // Log all request details for debugging

        // Assign request spec to RestAssured globally
        RestAssured.requestSpecification = requestSpecBuilder.build();

//        // Build the expected response specification
//        responseSpec = expect()
//                .statusCode(200) // Expect HTTP status code 200
//                .contentType(ContentType.JSON); // Expect JSON response

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        responseSpec = responseSpecBuilder.build(); // ✅ Correct assignment
    }

    @Test
    public void validateStatusCode() {
        // Send GET request and validate the response matches the expected specification
        get("/workspaces")
                .then()
                .spec(responseSpec); // Apply response spec
    }

    @Test
    public void validateResponseBody() {
        // Send GET request, extract response and validate a specific field
        Response response = get("/workspaces")
                .then()
                .spec(responseSpec) // Apply response spec
                .extract()
                .response();

        // Extract the name of the first workspace and assert it matches the expected value
        String workspaceName = response.path("workspaces[0].name").toString();
        assertThat("Workspace name should be 'My Workspace'", workspaceName, equalTo("My Workspace"));
    }
}