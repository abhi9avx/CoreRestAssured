package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

import com.reqres.base.BaseTest;
import java.io.IOException;

public class AutomateGet extends BaseTest {

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup();
    }

    @Test
    public void validateGetStatusCode() {
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("data[0].email", equalTo("michael.lawson@reqres.in"));
    }

    @Test
    public void extract_response() {
        Response res = given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        // Removed debug print statement for clean console output
        // System.out.println("Response: " + res.asString());
    }
    @Test
    public void extract_single_value_from_response(){
        Response res = given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(res.asString());
        // Removed debug print statements for clean console output
        // System.out.println("First user email: " + jsonPath.getString("data[0].email"));
        // System.out.println("First user first name: " + res.path("data[0].first_name"));


    }

    @Test
    public void hamcrest_assert_on_extracted_response(){
        String email = given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().path("data[0].email");
        // Removed debug print statements for clean console output
        // System.out.println("First user email: " + email);

        Assert.assertEquals(email,"michael.lawson@reqres.in");
    }

    @Test
    public void hamcrest_assertions_inside_body() {
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)

                .body("data", hasSize(6))

                .body("data[0].email", equalTo("michael.lawson@reqres.in"))

                .body("data.email", everyItem(notNullValue()));
    }

    @Test
    public void request_response_logging() {
        given()
                .spec(requestSpec)

                .log().method()
                .log().uri()
                .log().headers()
                .log().params()
                .log().body()

                .when()
                .get("/api/users?page=2")

                .then()

                .log().status()
                .log().body()

                .log().ifError()

                .statusCode(200);
    }

    @Test
    public void masked_logging_example() {
        given()
                .spec(requestSpec)
                .config(config().logConfig(logConfig().blacklistHeader("x-api-key")))
                .log().all()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200);
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

//✅ Test Case 3: extract_single_value_from_response():
//	•	Extract a specific value from a JSON response.
//	•	Use both JsonPath and Response.path() for accessing nested fields.
//	•	Understand which method to use depending on the use case (JsonPath for parsing complex structures, .path() for simpler access).

//✅Test Case 4: hamcrest_assert_on_extracted_response() :
//	•	Learned how to:
//	•	Extract a field directly from response using .path().
//	•	Use TestNG assertions (Assert.assertEquals) for clear pass/fail results.
//	•	Combine REST Assured's fluent syntax with custom validation logic.

//✅Test Case 5: hamcrest_assertions_inside_body() Test Case
//	1.	Hamcrest Integration with REST Assured:
//	•	Learned how to use Hamcrest matchers directly inside the .body() method to perform fluent, readable, and expressive validations on API responses.
//	2.	Validating Response List:
//	•	Used hasSize(n) to assert the number of items in the workspaces list.
//	•	Used not(empty()) to ensure the list isn't empty.
//	3.	Validating Specific Field Values:
//	•	Validated a specific field's value using equalTo().
//	•	Checked presence of an expected item in a list using hasItem().
//	4.	Null-Safety and Data Quality:
//	•	Ensured all workspace names are non-null with everyItem(notNullValue()).
//	5.	Clean Assertion Style:
//	•	Applied a concise, declarative style that avoids manual extraction, making tests easier to write, read, and maintain.


//🔍 Available Logging Options in REST Assured:
//.log() Method
//Description
//.log().all()
//Logs everything: request + response
//.log().method()
//Logs the HTTP method (GET, POST, etc.)
//.log().uri()
//Logs the full request URI
//.log().headers()
//Logs request headers
//.log().params()
//Logs query/form parameters
//.log().body()
//Logs request or response body
//.log().status()
//Logs the HTTP response status line
//.log().ifError()
//Logs only if validation fails or status code is not 2xx

//🔒 blacklistHeader("x-api-key")
//
//This tells REST Assured to hide or mask that header when logging, so it won't expose secrets in logs.
//
//��️ Why use it?
//	•	Prevents leaking API keys, tokens, auth headers, etc. in console or CI logs.
//	•	Very useful for security & compliance in teams and open environments.