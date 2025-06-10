package com.example.Spring_Security_May25.JwtFilter;

import com.example.Spring_Security_May25.Service.Utility.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;
    
    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        1. Extract Authorization header from the request.
        String authHeader = request.getHeader("Authorization");

//        2. check if the Authorization header is missing or doesn't start with "Bearer ". (Bearer(space))
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            3. if the header is invalid, pass the request to next filter in the chain and return.
            filterChain.doFilter(request, response);
            return;
        }

//        4. Extract the JWT from the Authorization header by the removing the "Bearer "
        String jwt = authHeader.substring(7);

        ///till now, we have received Authentication header, and we have extracted JWT token from 'Bearer '
        String userEmail;
        try {
//          5. use the JwtService to extract the username from the JWT;
            userEmail = jwtService.extractUserName(jwt);
        } catch (Exception ex) {
//            6. if an exception occurs during the userName extraction like invalid jwt so, pass the request to the next filter and return.
            filterChain.doFilter(request, response);
            return;
        }
//          7. check if the email was extracted successfully and if the use is not already authenticated.
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null){
//        8. load the use details from the database using the extracted email (userEmail)
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//          9. create an authentication object (UsernamePasswordAuthenticationToken) with user details
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            10. set details about the request in the Authentication object.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            11. se the Authentication Object in the SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
//      12. pass the request to the next filter in the chain
        filterChain.doFilter(request, response);
        return;

    }
}
