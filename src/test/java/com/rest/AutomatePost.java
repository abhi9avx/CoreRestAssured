package com.rest;

import com.utils.ConfigReader;
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

public class AutomatePost {

    @BeforeClass
    public void setup() {
        // Load values from config
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
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePostRequestBDDStyle() {
        // Define JSON payload
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"MyFirstWorkSpace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured Created this\",\n" +
                "        \"visibility\": \"personal\"\n" +
                "    }\n" +
                "}";

        // Perform POST request and validate response using BDD syntax
        given()
                .body(payload)
                .when()
                .post("/workspaces")
                .then()
                .assertThat()
                .body("workspace.name", equalTo("MyFirstWorkSpace"))
                .body("workspace.id", matchesPattern("^[a-z0-9-]+$")); // Adjust regex if needed
    }
}