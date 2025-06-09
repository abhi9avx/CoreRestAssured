package com.rest;

import com.reqres.utils.ConfigReader;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StaticImports {

    @Test
    public void testStaticImports() {
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");
        given()
                .baseUri(baseUrl)
                .header("x-api-key",apiKey)
                .when()
                .get("/workspaces")
                .then()
                .statusCode(200)
                .body("workspaces[0].name", equalTo("My Workspace")); // optional validation
    }
}