package com.rest;

import com.utils.ConfigReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecificationTest {

    private RequestSpecification reqSpec;

    @BeforeClass
    public void setup() {
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");

        // ✅ Reusable request specification
        reqSpec = given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .log().all(); // Optional: Log all request info
    }

    @Test
    public void validateGetStatusCode() {
        given()
                .spec(reqSpec) // ✅ Reuse request spec
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces[0].name", equalTo("My Workspace"));
    }

    @Test
    public void extract_response() {
        Response res = given()
                .spec(reqSpec)
                .when()
                .get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response: " + res.asString());
    }
}