package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import com.reqres.base.BaseTest;
import java.io.IOException;

public class ResponseSpecificationTest extends BaseTest {

    private ResponseSpecification responseSpec;

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method to get proper authentication
        
        // Response specification (requestSpec is inherited from BaseTest)
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void testWithResponseSpec() {
        given()
                .spec(requestSpec) // Use inherited requestSpec with proper authentication
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpec)
                .body("page", equalTo(2));
    }

    @Test
    public void validateStatusCode() {
        given()
                .spec(requestSpec) // Use inherited requestSpec with proper authentication
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpec)
                .body("page", equalTo(2));
    }

    @Test
    public void validateResponseBody() {
        Response response = given()
                .spec(requestSpec) // Use inherited requestSpec with proper authentication
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpec)
                .body("page", equalTo(2))
                .extract()
                .response();

        // Verify the first user's email
        String userEmail = response.path("data[0].email");
        assertThat("First user's email should be 'michael.lawson@reqres.in'", 
                  userEmail, 
                  equalTo("michael.lawson@reqres.in"));
        // Removed debug print statement for clean console output
        // System.out.println("Response: " + res.asString());
    }
}