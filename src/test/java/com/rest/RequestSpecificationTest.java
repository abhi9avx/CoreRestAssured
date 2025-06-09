package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.reqres.base.BaseTest;
import java.io.IOException;

public class RequestSpecificationTest extends BaseTest {

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method
    }

    @Test
    public void validateGetStatusCode() {
        given()
                .spec(requestSpec) // Use inherited requestSpec
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(2)); // Corrected for reqres.in user list response
    }

    @Test
    public void extract_response() {
        Response res = given()
                .spec(requestSpec) // Use inherited requestSpec
                .when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response: " + res.asString());
    }
}