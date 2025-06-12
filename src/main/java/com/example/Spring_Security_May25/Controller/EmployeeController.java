package com.example.Spring_Security_May25.Controller;

import com.example.Spring_Security_May25.Entity.Profile;
import com.example.Spring_Security_May25.Entity.User;
import com.example.Spring_Security_May25.Model.ProfileDto;
import com.example.Spring_Security_May25.Service.Employee.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/// this is accessible by the EMPLOYEE & ADMIN

@RestController
@RequestMapping("api/employee/v1")
@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @GetMapping("greet")
    public String greet(){
        return "hi this Employee dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("meet")
    public String meet(){
        return "hi this Admin but i can access this method from employee";
    }

    @PostMapping("create-profile")
    public ResponseEntity<String> createProfile(@Valid @RequestBody Profile profile, @AuthenticationPrincipal User user){
        String response = employeeService.saveProfile(profile, user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-profile")
    public ResponseEntity<ProfileDto> getProfile(@AuthenticationPrincipal User user){
        ProfileDto profile = employeeService.findProfile(user);
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

    @PutMapping("update-profile/{pId}")
    public ResponseEntity<String> updateProfile(@PathVariable("pId") Long profileId, @RequestBody Profile profile ){
        String response = employeeService.updateProfile(profile, profileId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





}
