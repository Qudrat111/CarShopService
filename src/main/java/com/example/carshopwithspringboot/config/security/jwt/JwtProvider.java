package com.example.carshopwithspringboot.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.expire.time}")
    private Integer expireTime;
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(String username) {
        long l = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(l))
                .setExpiration(new Date(l + expireTime))
                .signWith(secretKey)
                .claim("username", username)
                .compact();
        return token;
    }

    public boolean isValidToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    public String getSubjectFromToken(String token) {
        String subject = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject;

    }
}