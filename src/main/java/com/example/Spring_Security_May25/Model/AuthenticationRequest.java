package com.example.Spring_Security_May25.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**when user want to logining this DTO will Execute*/

@Data
@Builder
public class AuthenticationRequest {

    @NotBlank(message = "user name must be not Blank")
    @Email(message = "It must be an E-mail")
    private String userName; //E-mail

    @NotBlank(message = "password must be not Blank")
    private String password;
}
