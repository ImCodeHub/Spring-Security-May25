package com.example.Spring_Security_May25.Service.Utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${secret.key}")
    private String SECRET_KEY;

    /**
     * to get the signing key (will decode the SECRET_KEY into the Byte Array)
     * Keys.hmascShaKeyFor(decodeByteKey) is a method in the JWT library (for JAVA jwt)
     * */
    private Key getSigninKey(){
        byte[] decodeByteKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodeByteKey);
    }

    /**
     * populate authorities takes from the collection of authorities and return in String format*/
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities){
        Set<String> authoritiesSet = new HashSet<>(); //["ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_MANAGER", "ROLE_HR"]

        for(GrantedAuthority authority : authorities){
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet) ;//"ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_MANAGER, ROLE_HR"
    }

    /**this is generating jwt token*/
    public String generateToken(UserDetails user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", populateAuthorities(user.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token) //this is parseClaimsJws not parseClaimsJwt
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsFunction){
        Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    // extract userName from token payload>subject
    public String extractUserName(String token){
            return extractClaims(token, Claims::getSubject);
    }



}
