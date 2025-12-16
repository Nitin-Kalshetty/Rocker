package com.backend.rocker.security;

import com.backend.rocker.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.security.key}")
    private String SECRET_KEY;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    String generateJwtAccessToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("phone",user.getPhoneNumber())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }

    String getUsernameFromJwtToken(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

}
