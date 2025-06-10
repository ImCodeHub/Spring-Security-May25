package com.example.Spring_Security_May25.Model;

import com.example.Spring_Security_May25.Entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterRequest {
    @NotBlank(message = "field must not be Blank")
    @Pattern(regexp = "^[A-Za-z]$")
    private String firstName;

    @NotBlank(message = "field must not be Blank")
    @Pattern(regexp = "^[A-Za-z]$")
    private String lastName;

    @Email(message = "Invalid E-mail ID")
    @NotBlank(message = "field must not be Blank")
    private String email;

    @NotBlank(message = "field must not be Blank")
    @Size(min = 8, max = 12)
    private String password;

    @NotBlank(message = "field must not be Blank")
    @Pattern(regexp = "^[A-Z]$", message = "Character should be in Capital letter only")
    private Role role;
}
