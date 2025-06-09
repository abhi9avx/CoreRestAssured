package com.reqres.test;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;
import com.reqres.base.BaseTest;
import com.resreq.pojo.UserResponse;
import io.qameta.allure.*;
import static io.restassured.RestAssured.given;

@Feature("User API")
@Epic("User Management")
@Story("Single User Operations")
public class GetUserTest extends BaseTest {
    
    private static final String API_PATH = "/api/users";
    private static final int USER_ID = 2;
    
    @Test
    @Description("Test to verify fetching a single user by ID")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Single User")
    @Step("Making GET request to user API for ID={0}")
    public void getUser() {
        // Make the GET request
        Response response = makeGetRequest(USER_ID);
        
        // Verify response
        verifyResponse(response);
    }

    @Step("Making GET request to {0}/{1}")
    private Response makeGetRequest(int userId) {
        return given()
                .spec(requestSpec)
                .when()
                .get(API_PATH + "/" + userId);
    }

    @Step("Verifying response data for user")
    private void verifyResponse(Response response) {
        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // Deserialize and verify response data
        UserResponse userResponse = response.as(UserResponse.class);
        verifyUserData(userResponse);
        verifySupportData(userResponse);
    }

    @Step("Verifying user data")
    private void verifyUserData(UserResponse response) {
        assertThat("User response should not be null", response, notNullValue());
        assertEquals(response.getData().getId(), USER_ID, "User ID should be " + USER_ID);
        assertEquals(response.getData().getEmail(), "janet.weaver@reqres.in", "User email should match");
        assertEquals(response.getData().getFirst_name(), "Janet", "User first name should be Janet");
        assertEquals(response.getData().getLast_name(), "Weaver", "User last name should be Weaver");
        assertEquals(response.getData().getAvatar(), "https://reqres.in/img/faces/2-image.jpg", "User avatar URL should match");
    }

    @Step("Verifying support data")
    private void verifySupportData(UserResponse response) {
        assertThat("Support data should not be null", response.getSupport(), notNullValue());
        assertEquals(response.getSupport().getUrl(), "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral", "Support URL should match");
        assertEquals(response.getSupport().getText(), "Tired of writing endless social media content? Let Content Caddy generate it for you.", "Support text should match");
    }
} 