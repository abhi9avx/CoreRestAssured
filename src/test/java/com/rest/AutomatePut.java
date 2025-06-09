package com.rest;

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

import com.reqres.base.BaseTest;
import java.io.IOException;

/**
 * This class demonstrates how to perform a PUT request using Rest Assured
 * to update a Postman workspace and validate the response using BDD style assertions.
 */
public class AutomatePut extends BaseTest {

    // Reusable response specification
    private ResponseSpecification responseSpec;

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method

        // Setup response specification (can be made more generic in BaseTest if many tests use it)
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Test case: Validate PUT request using BDD style.
     * Updates an existing user and validates the response.
     */
    @Test
    public void validatePutRequestBDDStyle() {
        // User ID to be updated
        int userId = 2;

        // Payload for updating the user
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        // PUT request with path param and validations
        given()
                .spec(requestSpec) // Use inherited requestSpec
                .contentType(ContentType.JSON) // Explicitly set content type
                .pathParam("id", userId)
                .body(payload)
                .when()
                .put("/api/users/{id}")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("updatedAt", matchesPattern("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z$"));
    }
}