package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.reqres.base.BaseTest;
import java.io.IOException;
import org.testng.annotations.BeforeClass;

public class StaticImports extends BaseTest {

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method
    }

    @Test
    public void testStaticImports() {
        given()
                .spec(requestSpec) // Use inherited requestSpec
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[0].email", equalTo("michael.lawson@reqres.in")); // Adjusted for reqres.in response
    }
}