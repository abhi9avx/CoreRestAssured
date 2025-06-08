package com.resreq;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.resreq.pojo.EmptyUserResponse;

public class GetUserNotFoundTest {
    
    private Properties properties;
    
    @BeforeClass
    public void setup() throws IOException {
        properties = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        properties.load(fis);
    }
    
    @Test
    public void getUserNotFound() {
        RestAssured.baseURI = "https://reqres.in/api/users/23";

        Response response = RestAssured
        .given()
        .header("x-api-key", properties.getProperty("reqres.api.key"))
        .when()
        .get();

        // Verify status code
        assertEquals(response.getStatusCode(), 404);

        // Verify response body is empty
        EmptyUserResponse emptyResponse = response.as(EmptyUserResponse.class);
        assertThat(emptyResponse, notNullValue());
        assertEquals(response.getBody().asString(), "{}");
    }
}
