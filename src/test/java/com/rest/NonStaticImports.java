package com.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import com.reqres.base.BaseTest;
import java.io.IOException;

public class NonStaticImports extends BaseTest {

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method
    }

    @Test
    public void testNonStaticImports() {
        given()
                .spec(requestSpec) // Use inherited requestSpec
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[0].email", equalTo("michael.lawson@reqres.in")); // Adjusted for reqres.in response
    }
}