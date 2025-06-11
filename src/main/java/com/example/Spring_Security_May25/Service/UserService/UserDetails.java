package com.example.Spring_Security_May25.Service.UserService;

import com.example.Spring_Security_May25.Entity.User;
import com.example.Spring_Security_May25.Model.AuthenticationRequest;
import com.example.Spring_Security_May25.Model.AuthenticationResponse;
import com.example.Spring_Security_May25.Model.RegisterRequest;
import com.example.Spring_Security_May25.Repository.UserRepository;
import com.example.Spring_Security_May25.Service.Utility.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetails {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;


//    method to register a User
    public AuthenticationResponse saveUser(RegisterRequest registerRequest){
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    // method to login in Account
    public AuthenticationResponse Authentication(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword()
                )
        );
        User user = userRepository.findByEmail(authenticationRequest.getUserName()).orElseThrow(() -> new RuntimeException());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }


}


