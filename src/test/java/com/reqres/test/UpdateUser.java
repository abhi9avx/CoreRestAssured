package com.reqres.test;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.reqres.base.BaseTest;
import com.resreq.pojo.RequestUserDetails;
import com.resreq.pojo.ResponseUserDetails;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUser extends BaseTest {
    
    @Test
    @Description("Test updating an existing user's details")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Management")
    public void updateUserDetails(){

        RequestUserDetails userDetails = new RequestUserDetails();
        userDetails.setName("Abhishek");
        userDetails.setJob("QA");

        Response response = given()
        .spec(requestSpec)
        .contentType(ContentType.JSON)
        .body(userDetails)
        .when()
        .put("/api/users/2")
        .then()
        .extract()
        .response();

        Assert.assertEquals(response.getStatusCode(), 200);

        // Convert response to ResponseUserDetails object
        ResponseUserDetails userResponse = response.as(ResponseUserDetails.class);
        
        // Verify response data
        Assert.assertEquals(userResponse.getName(), userDetails.getName());
        Assert.assertEquals(userResponse.getJob(), userDetails.getJob());
        Assert.assertNotNull(userResponse.getUpdatedAt());

        System.out.println("Updated Name: " + userResponse.getName());
        System.out.println("Updated Job: " + userResponse.getJob());
        System.out.println("Updated At: " + userResponse.getUpdatedAt());
    }
}
