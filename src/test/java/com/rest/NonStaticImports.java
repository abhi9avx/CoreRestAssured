package com.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class NonStaticImports {

    @Test
    public void testNonStaticImports() {
        RestAssured
                .given()
                .baseUri("https://api.getpostman.com")
                .header("x-api-key", "POSTMAN_API_KEY")
                .when()
                .get("/workspaces")
                .then()
                .statusCode(200)
                .body("workspaces[0].name", equalTo("My Workspace"));
    }
}