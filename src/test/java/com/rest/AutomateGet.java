package com.rest;

import com.utils.ConfigReader;
import io.restassured.response.Response;
import org.apache.http.conn.util.PublicSuffixList;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AutomateGet {

    private String baseUrl;
    private String apiKey;

    @BeforeClass
    public void setup() {
        baseUrl = ConfigReader.getValue("base.url");
        apiKey = ConfigReader.getValue("postman.api.key");
    }

    @Test
    public void validateGetStatusCode() {
        given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces[0].name", equalTo("My Workspace"));
    }

    @Test
    public void extract_response() {
        Response res = given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response: " + res.asString());
    }

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

//✅ Test Case 1: validateGetStatusCode()
//	•	Purpose: To validate that the GET request to /workspaces endpoint:
//	•	Returns a 200 OK status code.
//	•	Has the expected workspace name "My Workspace" in the first element of the response.
//	•	Key Actions:
//	•	Uses .baseUri() and .header() to set API endpoint and authentication.
//	•	.get("/workspaces") sends the request.
//	•	.log().all() prints the full response for visibility.
//	•	.statusCode(200) ensures the API is working.
//	•	.body("workspaces[0].name", equalTo("My Workspace")) validates the response content.
//
//⸻
//
//✅ Test Case 2: extract_response()
//	•	Purpose: To extract and print the full response from the GET /workspaces API.
//	•	Key Actions:
//	•	Sends the same GET request as test case 1.
//	•	Uses .extract().response() to capture the response.
//	•	System.out.println(res.asString()) prints the raw JSON response to the console.