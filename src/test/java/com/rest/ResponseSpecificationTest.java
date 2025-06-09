package com.rest;

import com.reqres.utils.ConfigReader;
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
        // ✅ Request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("x-api-key", ConfigReader.getValue("postman.api.key"))
                .build();

        // ✅ Response specification
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .expectBody("workspaces[0].name", equalTo("My Workspace"))
                .build();
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