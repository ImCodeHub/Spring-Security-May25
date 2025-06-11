package com.example.Spring_Security_May25.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
        @JsonProperty("access_token")
    public String accessToken;
}
