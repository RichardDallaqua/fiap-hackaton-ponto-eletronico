package com.fiap.ponto.commons.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JwtValidator {
    private static final byte[] SECRET_KEY = "tQTN2PpCSlQncklIioGs2bYvmL/ul2KIWJriPlygFZs=".getBytes(StandardCharsets.UTF_8);


    public boolean validate(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY))
                    .build()
                    .parseSignedClaims(token).getPayload();
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
