package com.example.Spring_Security_May25.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {
    private Long profileId;
    private String fullName;
    private String email;
    private int age;
    private String mobile;
    private String city;
    private String address;
}
