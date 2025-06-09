package com.reqres.test;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import io.qameta.allure.*;
import static io.restassured.RestAssured.given;
import com.reqres.base.BaseTest;
import com.resreq.pojo.UserListResponse;

@Feature("User List API")
@Epic("User Management")
@Story("User List Operations")
public class GetUserListTest extends BaseTest {
    
    private static final String API_PATH = "/api/users";
    private static final int EXPECTED_PAGE = 2;
    private static final int EXPECTED_PER_PAGE = 6;
    private static final int EXPECTED_TOTAL = 12;
    private static final int EXPECTED_TOTAL_PAGES = 2;
    
    @Test
    @Description("Test to verify the user list API functionality with pagination")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get User List with Pagination")
    @Step("Making GET request to user list API with page={0}")
    public void getUserList() {
        // Make the GET request
        Response response = makeGetRequest(EXPECTED_PAGE);
        
        // Verify response
        verifyResponse(response);
    }
    
    @Step("Making GET request to {0} with page={1}")
    private Response makeGetRequest(int page) {
        return given()
                .spec(requestSpec)
                .when()
                .get(API_PATH + "?page=" + page);
    }
    
    @Step("Verifying response data")
    private void verifyResponse(Response response) {
        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Deserialize and verify response data
        UserListResponse userListResponse = response.as(UserListResponse.class);
        verifyUserListResponse(userListResponse);
        verifyFirstUserData(userListResponse);
        verifySupportData(userListResponse);
    }
    
    @Step("Verifying user list response data")
    private void verifyUserListResponse(UserListResponse response) {
        assertThat("Response should not be null", response, notNullValue());
        assertEquals(response.getPage(), EXPECTED_PAGE, "Page number should be " + EXPECTED_PAGE);
        assertEquals(response.getPerPage(), EXPECTED_PER_PAGE, "Per page count should be " + EXPECTED_PER_PAGE);
        assertEquals(response.getTotal(), EXPECTED_TOTAL, "Total users should be " + EXPECTED_TOTAL);
        assertEquals(response.getTotalPages(), EXPECTED_TOTAL_PAGES, "Total pages should be " + EXPECTED_TOTAL_PAGES);
    }
    
    @Step("Verifying first user data")
    private void verifyFirstUserData(UserListResponse response) {
        UserListResponse.UserData firstUser = response.getData().get(0);
        assertEquals(firstUser.getId(), 7, "First user ID should be 7");
        assertEquals(firstUser.getEmail(), "michael.lawson@reqres.in", "First user email should match");
        assertEquals(firstUser.getFirstName(), "Michael", "First user first name should be Michael");
        assertEquals(firstUser.getLastName(), "Lawson", "First user last name should be Lawson");
        assertEquals(firstUser.getAvatar(), "https://reqres.in/img/faces/7-image.jpg", "First user avatar URL should match");
    }
    
    @Step("Verifying support data")
    private void verifySupportData(UserListResponse response) {
        assertThat("Support data should not be null", response.getSupport(), notNullValue());
        assertEquals(response.getSupport().getUrl(), 
            "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
            "Support URL should match");
        assertEquals(response.getSupport().getText(), 
            "Tired of writing endless social media content? Let Content Caddy generate it for you.",
            "Support text should match");
    }
} 