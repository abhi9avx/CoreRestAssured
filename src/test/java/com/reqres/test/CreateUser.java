package com.reqres.test;

import static io.restassured.RestAssured.given;
import com.reqres.base.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.http.ContentType;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.resreq.pojo.RequestUserDetails;
import com.resreq.pojo.ResponseUserDetails;

import io.restassured.response.Response;

public class CreateUser extends BaseTest {
    @Test
    @Description("Test creating a new user with valid details")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Management")
    public void createNewUser(){

        RequestUserDetails userDetails = new RequestUserDetails();
        userDetails.setName("Abhinav");
        userDetails.setJob("SDET");

        Response response = given()
        .spec(requestSpec)
            .contentType(ContentType.JSON)
            .body(userDetails)
            .when()
            .post("/api/users")
            .then()
            .extract()
            .response();

            // Verify status code
        Assert.assertEquals(response.getStatusCode(), 201);

        // Convert response to LoginResponse object
        ResponseUserDetails userResponse = response.as(ResponseUserDetails.class);
        
        // Verify response data
        Assert.assertNotNull(userResponse.getId());
        Assert.assertEquals(userResponse.getName(), userDetails.getName());
        Assert.assertEquals(userResponse.getJob(), userDetails.getJob());
    }
    
}
