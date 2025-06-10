package com.example.Spring_Security_May25.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    public String accessToken;
}
