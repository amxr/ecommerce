package com.amir.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JWTToken {
    @JsonProperty("jwt-token")
    private String jwtToken;
}
