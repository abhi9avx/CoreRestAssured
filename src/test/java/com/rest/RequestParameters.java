package com.rest;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * This class contains test examples to demonstrate how to use Rest Assured
 * for sending HTTP requests with query parameters, path parameters,
 * form-data (multipart), and file download/upload.
 */
public class RequestParameters {

    // Common base URI for Postman Echo API
    private static final String BASE_URI = "https://postman-echo.com";

    /**
     * Example 1: Send a GET request with a single query parameter and validate the response.
     */
    @Test
    public void testSingleQueryParameter() {
        given()
                .baseUri(BASE_URI)
                .queryParam("foo2", "bar2") // Add single query parameter
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("args.foo2", equalTo("bar2")); // Validate query param in response
    }

    /**
     * Example 2: Send a GET request with multiple query parameters using a HashMap.
     */
    @Test
    public void testMultipleQueryParameters() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("foo5", "bar5");
        queryParams.put("foo6", "bar6");

        given()
                .baseUri(BASE_URI)
                .queryParams(queryParams) // Add all query params from map
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("args.foo5", equalTo("bar5"))
                .body("args.foo6", equalTo("bar6"));
    }

    /**
     * Example 3: Send a GET request with a query parameter having multiple values.
     */
    @Test
    public void testMultipleValueQueryParameters() {
        given()
                .baseUri(BASE_URI)
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
                .baseUri("https://reqres.in")
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
     */
    @Test
    public void multipartFormDataExample() {
        given()
                .baseUri(BASE_URI)
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
     */
    @Test
    public void uploadFileWithAdditionalData() {
        String attributes = "{\"name\" : \"temp.txt\", \"parent\" : {\"id\" : \"1234\"}}";

        given()
                .baseUri(BASE_URI)
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
        String githubBaseUri = "https://github.com";
        String filePath = "/abhi9avx/CoreRestAssured/raw/refs/heads/main/temp.txt";

        // Send GET request to download file as byte array
        byte[] fileBytes = given()
                .baseUri(githubBaseUri)
                .log().uri()
                .when()
                .get(filePath)
                .then()
                .log().status()
                .assertThat()
                .statusCode(200)
                .extract()
                .asByteArray();

        // Save byte array to local file
        File outputFile = new File("temp.txt");
        try (OutputStream os = new FileOutputStream(outputFile)) {
            os.write(fileBytes);
            System.out.println("✅ File downloaded successfully: " + outputFile.getAbsolutePath());
        }
    }

    @Test
    public void formUrlEncodedExample() {
        given()
                .baseUri("https://postman-echo.com")
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