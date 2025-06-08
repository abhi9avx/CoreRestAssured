package com.reqres.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;
import com.reqres.base.BaseTest;
import com.resreq.pojo.ResourceListResponse;

public class GetResourceListTest extends BaseTest {
    
    @Test
    public void getResourceList() {
        // Make the GET request with required header
        Response response = RestAssured
        .given()
        .spec(requestSpec)
        .when()
        .get("/api/unknown");

        // Deserialize the response into ResourceListResponse object
        ResourceListResponse resourceResponse = response.as(ResourceListResponse.class);

        // Verify status code
        assertEquals(response.getStatusCode(), 200);

        // Verify response data
        assertThat(resourceResponse, notNullValue());
        assertEquals(resourceResponse.getPage(), 1);
        assertEquals(resourceResponse.getPer_page(), 6);
        assertEquals(resourceResponse.getTotal(), 12);
        assertEquals(resourceResponse.getTotal_pages(), 2);
        
        // Verify first resource data
        assertEquals(resourceResponse.getData().get(0).getId(), 1);
        assertEquals(resourceResponse.getData().get(0).getName(), "cerulean");
        assertEquals(resourceResponse.getData().get(0).getYear(), 2000);
        assertEquals(resourceResponse.getData().get(0).getColor(), "#98B2D1");
        assertEquals(resourceResponse.getData().get(0).getPantone_value(), "15-4020");

        // Verify support data
        assertThat(resourceResponse.getSupport(), notNullValue());
        assertEquals(resourceResponse.getSupport().getUrl(), "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");
        assertEquals(resourceResponse.getSupport().getText(), "Tired of writing endless social media content? Let Content Caddy generate it for you.");
    }
} 