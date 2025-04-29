package com.dyno.climb.user.domain.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final String secretKey = "your_secret_key1"; // Should be kept safe and not hardcoded

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(
            new Date(System.currentTimeMillis() + 365L * 1000 * 60 * 60 * 24)) // 10 hours
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Claims extractClaims(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  public boolean isTokenExpired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }

  public boolean validateToken(String token, String username) {
    return (username.equals(extractUsername(token)) && !isTokenExpired(token));
  }
}
