package com.resreq;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.resreq.pojo.UserResponse;

public class GetUserTest {
    
    private Properties properties;
    
    @BeforeClass
    public void setup() throws IOException {
        // Load properties file
        properties = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        properties.load(fis);
    }
    
    @Test
    public void getUserDetails() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/users/1";
        
        // Make the GET request with required header
        Response response = RestAssured
        .given()
        .header("x-api-key", properties.getProperty("reqres.api.key"))
        .when()
        .get();

        // Deserialize the response into UserResponse object
        UserResponse userResponse = response.as(UserResponse.class);

        // Print user details for verification
        System.out.println("Name: " + userResponse.getData().getFirst_name() + " " + userResponse.getData().getLast_name());
        System.out.println("Email: " + userResponse.getData().getEmail());

        // Verify the response data using assertions
        assertEquals(userResponse.getData().getId(), 1);
        assertEquals(userResponse.getData().getEmail(), "george.bluth@reqres.in");
    }
}
