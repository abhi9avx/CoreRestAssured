package com.rest;

import com.reqres.utils.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.Test;


import static org.hamcrest.Matchers.equalTo;

public class NonStaticImports {

    @Test
    public void testNonStaticImports() {
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");
        RestAssured
                .given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/workspaces")
                .then()
                .statusCode(200)
                .body("workspaces[0].name", equalTo("My Workspace"));
    }
}