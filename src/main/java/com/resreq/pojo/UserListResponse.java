package com.resreq.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserListResponse {
    private int page;
    
    @JsonProperty("per_page")
    private int perPage;
    
    private int total;
    
    @JsonProperty("total_pages")
    private int totalPages;
    
    private List<UserData> data;
    private Support support;
    
    @Data
    public static class UserData {
        private int id;
        private String email;
        
        @JsonProperty("first_name")
        private String firstName;
        
        @JsonProperty("last_name")
        private String lastName;
        
        private String avatar;
    }
    
    @Data
    public static class Support {
        private String url;
        private String text;
    }
} 