package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StaticImports {

    @Test
    public void testStaticImports() {
        given()
                .baseUri("https://api.getpostman.com")
                .header("x-api-key", "POSTMAN_API_KEY")
                .when()
                .get("/workspaces")
                .then()
                .statusCode(200)
                .body("workspaces[0].name", equalTo("My Workspace")); // optional validation
    }
}