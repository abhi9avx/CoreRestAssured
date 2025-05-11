package com.rest;

import com.utils.ConfigReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.conn.util.PublicSuffixList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

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
    @Test
    public void extract_single_value_from_response(){
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

        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println("workspaces name: " + jsonPath.getString("workspaces[0].name"));
        System.out.println("workspaces name: " + res.path("workspaces[0].type"));


    }

    @Test
    public void hamcrest_assert_on_extracted_response(){
        String name = given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().path("workspaces[0].name");
        System.out.println("workspaces name: " + name);

        Assert.assertEquals(name,"My Workspace");
    }

    @Test
    public void hamcrest_assertions_inside_body() {
        given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200)

                // ✅ Validate size of workspaces list
                .body("workspaces", hasSize(1))

                // ✅ Validate workspace name is exactly "My Workspace"
                .body("workspaces[0].name", equalTo("My Workspace"))

                // ✅ Validate workspace names list is not empty
                .body("workspaces.name", not(empty()))

                // ✅ Validate workspace names list contains specific value
                .body("workspaces.name", hasItem("My Workspace"))

                // ✅ Validate all workspace names are non-null
                .body("workspaces.name", everyItem(notNullValue()));
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
//	•	Combine REST Assured’s fluent syntax with custom validation logic.

//✅Test Case 5: hamcrest_assertions_inside_body() Test Case
//	1.	Hamcrest Integration with REST Assured:
//	•	Learned how to use Hamcrest matchers directly inside the .body() method to perform fluent, readable, and expressive validations on API responses.
//	2.	Validating Response List:
//	•	Used hasSize(n) to assert the number of items in the workspaces list.
//	•	Used not(empty()) to ensure the list isn’t empty.
//	3.	Validating Specific Field Values:
//	•	Validated a specific field’s value using equalTo().
//	•	Checked presence of an expected item in a list using hasItem().
//	4.	Null-Safety and Data Quality:
//	•	Ensured all workspace names are non-null with everyItem(notNullValue()).
//	5.	Clean Assertion Style:
//	•	Applied a concise, declarative style that avoids manual extraction, making tests easier to write, read, and maintain.