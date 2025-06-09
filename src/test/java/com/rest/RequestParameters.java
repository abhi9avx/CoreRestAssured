package com.rest;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.reqres.base.BaseTest;

/**
 * This class contains test examples to demonstrate how to use Rest Assured
 * for sending HTTP requests with query parameters, path parameters,
 * form-data (multipart), and file download/upload.
 */
public class RequestParameters extends BaseTest {

    // BASE_URI will be inherited from BaseTest for reqres.in specific tests
    // private static final String BASE_URI = "https://postman-echo.com";

    @Override
    @BeforeClass
    public void setup() throws IOException {
        super.setup(); // Call BaseTest's setup method
    }

    /**
     * Example 1: Send a GET request with a single query parameter and validate the response.
     */
    @Test
    public void testSingleQueryParameter() {
        given()
                .spec(requestSpec) // Use inherited requestSpec
                .queryParam("page", "2") // Adjusted for reqres.in
                .log().all()
                .when()
                .get("/api/users") // Adjusted for reqres.in
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(2)); // Validate query param in response
    }

    /**
     * Example 2: Send a GET request with multiple query parameters using a HashMap.
     */
    @Test
    public void testMultipleQueryParameters() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "2");
        queryParams.put("per_page", "6");

        given()
                .spec(requestSpec) // Use inherited requestSpec
                .queryParams(queryParams) // Add all query params from map
                .log().all()
                .when()
                .get("/api/users") // Adjusted for reqres.in
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("per_page", equalTo(6));
    }

    /**
     * Example 3: Send a GET request with a query parameter having multiple values.
     * This test is DISABLED as reqres.in does not directly support this pattern for user queries.
     */
    @Test(enabled = false) // Disabled as reqres.in doesn't support this directly
    public void testMultipleValueQueryParameters() {
        given()
                .baseUri("https://postman-echo.com") // Use postman-echo.com for this specific test
                .queryParam("foo", "bar1", "bar2", "bar3") // foo=bar1&foo=bar2&foo=bar3
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    /**
     * Example 4: Send a GET request using a path parameter to retrieve a user.
     */
    @Test
    public void testPathParameter() {
        given()
                .spec(requestSpec) // Use inherited requestSpec
                .pathParam("id", 2) // Replaces {id} in URL
                .log().all()
                .when()
                .get("/api/users/{id}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    /**
     * Example 5: Send a POST request with multiple form-data key-value pairs.
     * This test is for postman-echo.com as reqres.in does not support this directly.
     */
    @Test
    public void multipartFormDataExample() {
        given()
                .baseUri("https://postman-echo.com") // Use postman-echo.com for this specific test
                .multiPart("foo1", "bar1")
                .multiPart("foo2", "bar2")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    /**
     * Example 6: Upload a file along with additional JSON attributes as form-data.
     * This test is for postman-echo.com as reqres.in does not support this directly.
     */
    @Test
    public void uploadFileWithAdditionalData() {
        String attributes = "{\"name\" : \"temp.txt\", \"parent\" : {\"id\" : \"1234\"}}";

        given()
                .baseUri("https://postman-echo.com") // Use postman-echo.com for this specific test
                .multiPart("file", new File("temp.txt")) // Upload file
                .multiPart("attributes", attributes, "application/json") // Add metadata
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    /**
     * Example 7: Download a file from GitHub and save it locally as 'temp.txt'.
     */
    @Test
    public void downloadFile() throws IOException {
        // This test is for downloading a specific file from GitHub, not reqres.in
        String githubBaseUri = "https://raw.githubusercontent.com"; // Correct raw content URL
        String filePath = "/abhi9avx/CoreRestAssured/main/pom.xml"; // Example: Download pom.xml

        // Send GET request to download file as byte array
        byte[] fileBytes = given()
                .baseUri(githubBaseUri)
                .log().uri()
                .when()
                .get(filePath)
                .then()
                .log().status()
                .assertThat()
                .statusCode(200) // Expect 200 OK
                .extract()
                .asByteArray();

        // Save byte array to local file
        File outputFile = new File("downloaded_pom.xml"); // Save as downloaded_pom.xml
        try (OutputStream os = new FileOutputStream(outputFile)) {
            os.write(fileBytes);
            System.out.println("✅ File downloaded successfully: " + outputFile.getAbsolutePath());
        } finally {
            // Clean up the downloaded file after the test
            if (outputFile.exists()) {
                outputFile.delete();
            }
        }
    }

    @Test
    public void formUrlEncodedExample() {
        given()
                .baseUri("https://postman-echo.com") // Use postman-echo.com for this specific test
                .config(config()
                        .encoderConfig(
                                EncoderConfig.encoderConfig()
                                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)
                        )
                )
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }
} 