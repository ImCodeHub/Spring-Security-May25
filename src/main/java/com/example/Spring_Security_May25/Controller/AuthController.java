package com.example.Spring_Security_May25.Controller;

import com.example.Spring_Security_May25.Model.AuthenticationRequest;
import com.example.Spring_Security_May25.Model.AuthenticationResponse;
import com.example.Spring_Security_May25.Model.RegisterRequest;
import com.example.Spring_Security_May25.Service.UserService.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/// this is the public Accessible

@CrossOrigin
@RestController
@RequestMapping("api/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final UserDetails userDetails;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        AuthenticationResponse authenticationResponse = userDetails.saveUser(registerRequest);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse authentication = userDetails.Authentication(authenticationRequest);
        return new ResponseEntity<>(authentication, HttpStatus.OK);
    }



}
