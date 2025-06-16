package com.reqres.test;

import com.reqres.base.BaseTest;
import com.resreq.pojo.LoginRequest;
import com.resreq.pojo.LoginResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginSuccesful extends BaseTest {
    
    @Test
    @Description("Test successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Authentication")
    public void testSuccessfulLogin() {
        // Create login request object
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("eve.holt@reqres.in");
        loginRequest.setPassword("cityslicka");
        
        // Make the POST request using the requestSpec from BaseTest
        Response response = given()
            .spec(requestSpec)
            .contentType(ContentType.JSON)
            .body(loginRequest)
            .when()
            .post("/api/login")
            .then()
            .extract()
            .response();
        
        // Verify status code
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // Convert response to LoginResponse object
        LoginResponse loginResponse = response.as(LoginResponse.class);
        
        // Verify response body contains token
        Assert.assertNotNull(loginResponse.getToken());
        Assert.assertEquals(loginResponse.getToken(), "QpwL5tke4Pnpja7X4");
    }
}
