package com.rest;

import com.reqres.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;


/**
 * This class demonstrates how to automate POST requests using Rest Assured.
 * Covers: BDD style, Non-BDD style, and Map-based payload for workspace creation.
 */
public class AutomatePost {

    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");

        // Common request specification
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("X-Api-Key", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        // Common response specification
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * BDD-style test to validate workspace creation using a JSON file.
     */
    @Test
    public void validatePostRequest_BDD_Style() {
        File file = new File("src/main/resources/CreateWorkspacePayload.json");

        given()
                .body(file)
                .when()
                .post("/workspaces")
                .then()
                .assertThat()
                .body("workspace.name", equalTo("MyFirstWorkSpace5"))
                .body("workspace.id", matchesPattern("^[a-z0-9-]+$")); // Workspace ID format
    }

    /**
     * Non-BDD style test to validate workspace creation using a string payload.
     */
    @Test
    public void validatePostRequest_NonBDD_Style() {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"MyFirstWorkSpace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured Created this\",\n" +
                "        \"visibility\": \"personal\"\n" +
                "    }\n" +
                "}";

        Response response = given()
                .body(payload)
                .post("/workspaces");

        assertThat("Workspace name should match",
                response.path("workspace.name"), equalTo("MyFirstWorkSpace"));

        assertThat("Workspace ID should match expected pattern",
                response.path("workspace.id"), matchesPattern("^[a-z0-9-]+$"));
    }

    /**
     * BDD-style test to create a workspace using a HashMap as payload.
     */
    @Test
    public void validatePostRequest_UsingMapPayload() {
        HashMap<String, Object> mainObject = new HashMap<>();
        HashMap<String, String> workspaceDetails = new HashMap<>();

        workspaceDetails.put("name", "MyFirstWorkSpace6");
        workspaceDetails.put("type", "personal");
        workspaceDetails.put("description", "Rest Assured Created this");
        workspaceDetails.put("visibility", "personal");

        mainObject.put("workspace", workspaceDetails);

        given()
                .body(mainObject)
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .body("workspace.name", equalTo("MyFirstWorkSpace6"))
                .body("workspace.id", matchesPattern("^[a-z0-9-]+$"));
    }

}