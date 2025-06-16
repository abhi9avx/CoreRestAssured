package com.reqres.test;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.reqres.base.BaseTest;
import io.qameta.allure.*;
import static io.restassured.RestAssured.given;

@Feature("User API")
@Epic("User Management")
@Story("Single User Operations")
public class GetUserNotFoundTest extends BaseTest {
    
    private static final String API_PATH = "/api/users";
    private static final int NON_EXISTENT_USER_ID = 23;
    
    @Test
    @Description("Test retrieving a non-existent user")
    @Severity(SeverityLevel.NORMAL)
    @Story("User Management")
    @Step("Making GET request for non-existent user with ID={0}")
    public void getUserNotFound() {
        // Make the GET request
        Response response = makeGetRequest(NON_EXISTENT_USER_ID);

        // Verify response
        verifyNotFoundResponse(response);
    }

    @Step("Making GET request to {0}/{1}")
    private Response makeGetRequest(int userId) {
        return given()
                .spec(requestSpec)  // Use the requestSpec from BaseTest
                .pathParam("userId", userId)
                .when()
                .get(API_PATH + "/{userId}")
                .then()
                .statusCode(404)
                .extract()
                .response();
    }

    @Step("Verifying 404 Not Found response for non-existent user")
    private void verifyNotFoundResponse(Response response) {
        // Verify status code
        assertEquals(response.getStatusCode(), 404, "Status code should be 404");

        // Verify response body is empty
        assertEquals(response.getBody().asString(), "{}", "Response body should be empty for 404");
    }
}
