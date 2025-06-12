package com.example.Spring_Security_May25.Service.Employee;

import com.example.Spring_Security_May25.Entity.Profile;
import com.example.Spring_Security_May25.Entity.User;
import com.example.Spring_Security_May25.Model.ProfileDto;
import com.example.Spring_Security_May25.Repository.ProfileRepository;
import com.example.Spring_Security_May25.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeInterface{

    @Autowired
    private final ProfileRepository profileRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public String saveProfile(Profile profile, User user) {
        profile.setUser(user);
        profileRepository.save(profile);
        return "your Profile has been saved now";
    }

    @Override
    public ProfileDto findProfile(User user) {
        Profile existProfile = profileRepository.findByUserUserId(user.getUserId()).orElseThrow(() -> new RuntimeException("Profile not found"));

        ProfileDto profileDto = ProfileDto.builder()
                .profileId(existProfile.getProfileId())
                .fullName(user.getFirstName()+" "+user.getLastName())
                .email(user.getEmail())
                .age(existProfile.getAge())
                .address(existProfile.getAddress())
                .city(existProfile.getCity())
                .mobile(existProfile.getMobile())
                .build();

        return profileDto;
    }

    @Override
    public String updateProfile(Profile profile, Long profileId) {
        Profile foundProfile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not found"));

        foundProfile.setAge(profile.getAge());
        foundProfile.setMobile(profile.getMobile());
        foundProfile.setCity(profile.getCity());
        foundProfile.setAddress(profile.getAddress());

        profileRepository.save(foundProfile);

        return "profile has been updated!";
    }
}
