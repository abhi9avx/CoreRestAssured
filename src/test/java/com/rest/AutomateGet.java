package com.rest;

import com.utils.ConfigReader;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AutomateGet {

    @Test
    public void validateGetStatusCode() {
        String baseUrl = ConfigReader.getValue("base.url");
        String apiKey = ConfigReader.getValue("postman.api.key");

        given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/workspaces")
                .then()
                .log().all() // Logs full response - headers, body, status
                .assertThat()
                .statusCode(200)
                .body("workspaces[0].name", equalTo("My Workspace"));
    }

    // 🧠 Summary:
    // given() -> Set up the request (base URI, headers)
    // when()  -> Define the HTTP method (GET)
    // then()  -> Validate response (status code, body)

    // 📋 About .log().all():
    // - Logs full response data to the console (headers + body + status).
    // - Helps during debugging to see what response was returned.

    // ✅ If the test passes:
    //    - .log().all() shows the complete response, helping you confirm correctness.

    // ❌ If the test fails (like wrong status or body mismatch):
    //    - .log().all() still shows the response.
    //    - This helps you **debug quickly** by seeing what actually came back.

    // 🔎 You can also use .log().ifError() to log only when the test fails.
}