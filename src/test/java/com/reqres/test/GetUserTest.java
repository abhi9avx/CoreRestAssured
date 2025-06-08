package com.reqres.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;
import com.reqres.base.BaseTest;
import com.resreq.pojo.UserResponse;

public class GetUserTest extends BaseTest {
    
    @Test
    public void getUser() {
        // Make the GET request with required header
        Response response = RestAssured
        .given()
        .spec(requestSpec)
        .when()
        .get("/api/users/2");

        // Deserialize the response into UserResponse object
        UserResponse userResponse = response.as(UserResponse.class);

        // Verify status code
        assertEquals(response.getStatusCode(), 200);

        // Verify response data
        assertThat(userResponse, notNullValue());
        assertEquals(userResponse.getData().getId(), 2);
        assertEquals(userResponse.getData().getEmail(), "janet.weaver@reqres.in");
        assertEquals(userResponse.getData().getFirst_name(), "Janet");
        assertEquals(userResponse.getData().getLast_name(), "Weaver");
        assertEquals(userResponse.getData().getAvatar(), "https://reqres.in/img/faces/2-image.jpg");

        // Verify support data
        assertThat(userResponse.getSupport(), notNullValue());
        assertEquals(userResponse.getSupport().getUrl(), "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");
        assertEquals(userResponse.getSupport().getText(), "Tired of writing endless social media content? Let Content Caddy generate it for you.");
    }
} 