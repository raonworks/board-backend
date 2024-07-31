package com.raonworks.boardback.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

  @Value("${secret-key}")
  private String secretKey;

  public String create(String email) {
    Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
    SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    return Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .compact();
  }

  public String validate(String jwt) {
    Claims claims = null;
    SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    try {
      claims = Jwts.parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(jwt)
              .getBody();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return claims.getSubject();
  }
}
