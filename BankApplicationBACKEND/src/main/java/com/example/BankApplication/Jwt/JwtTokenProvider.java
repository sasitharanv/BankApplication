package com.example.BankApplication.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationMilliseconds;

    // Generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMilliseconds);

        // Use explicit charset for encoding
        byte[] keyBytes = jwtSecret.getBytes(Charset.forName("UTF-8"));
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Get username from JWT token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(Charset.forName("UTF-8"))))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(Charset.forName("UTF-8"))))
                    .build()
                    .parseClaimsJws(token);
            // No need to explicitly check for expiration here, as JJWT does it during parsing
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token: " + e.getMessage());
            return false; // Explicitly handle expired JWT tokens
        } catch (JwtException | IllegalArgumentException e) {
            // Cover all other JwtExceptions and IllegalArgumentException
            System.out.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }

    // Check if the token has expired - This method is now optional
    // as JJWT's parseClaimsJws method automatically checks the token expiration.
    private boolean isTokenExpired(String token) {
        // Implementation not required if JJWT's automatic expiration check is relied upon
        return false;
    }

    // This method remains the same as it already uses Charset.forName("UTF-8")
    private SecretKey key() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(Charset.forName("UTF-8")));
    }
}
