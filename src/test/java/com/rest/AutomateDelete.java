package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.reqres.base.BaseTest;
import java.io.IOException;

/**
 * This class demonstrates how to automate a DELETE request using Rest Assured
 * to remove a user from reqres.in and verify the response.
 */
public class AutomateDelete extends BaseTest {

    // Remove explicit RequestSpecification as it will be inherited from BaseTest
    // private RequestSpecification requestSpec;

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method
    }

    /**
     * Test to validate DELETE operation for a user by ID on reqres.in.
     */
    @Test
    public void validateDeleteRequest() {
        RestAssured.responseSpecification = null; // Clear any global response spec
        int userId = 2; // Example: delete user with ID 2

        given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .when()
                .delete("/api/users/{id}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(204) // Expect 204 No Content for successful delete
                .contentType(""); // Explicitly expect no content type for 204 response
    }

    @Test
    public void deleteUser() {
        RestAssured.responseSpecification = null; // Clear any global response spec
        given()
                .spec(requestSpec)
                .when()
                .delete("/api/users/2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(204) // Expect 204 No Content for successful delete
                .contentType(""); // Explicitly expect no content type for 204 response
    }
}