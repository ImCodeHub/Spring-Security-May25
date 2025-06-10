package com.example.Spring_Security_May25.Controller;

import com.example.Spring_Security_May25.Model.AuthenticationResponse;
import com.example.Spring_Security_May25.Model.RegisterRequest;
import com.example.Spring_Security_May25.Service.UserService.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
