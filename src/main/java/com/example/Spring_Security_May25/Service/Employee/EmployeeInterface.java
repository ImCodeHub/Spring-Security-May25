package com.example.Spring_Security_May25.Service.Employee;

import com.example.Spring_Security_May25.Entity.Profile;
import com.example.Spring_Security_May25.Entity.User;
import com.example.Spring_Security_May25.Model.ProfileDto;

public interface EmployeeInterface {
    public String saveProfile(Profile profile, User user);
    public ProfileDto findProfile(User user);
    public String updateProfile(Profile profile, Long profileId);
}
