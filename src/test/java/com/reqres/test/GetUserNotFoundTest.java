package com.reqres.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.reqres.base.BaseTest;

public class GetUserNotFoundTest extends BaseTest {
    
    @Test
    public void getUserNotFound() {
        // Make the GET request with required header
        Response response = RestAssured
        .given()
        .spec(requestSpec)
        .when()
        .get("/api/users/23");

        // Verify status code
        assertEquals(response.getStatusCode(), 404);

        // Verify response body is empty
        assertEquals(response.getBody().asString(), "{}");
    }
}
