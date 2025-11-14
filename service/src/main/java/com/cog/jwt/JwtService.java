package com.cog.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    try {
      PublicKey publicKey = KeyLoader.getPublicKey();
      return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();
    } catch (Exception e) {
      throw new RuntimeException("Invalid or expired JWT token", e);
    }
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public String generateToken(String username) throws Exception {
    PrivateKey privateKey = KeyLoader.getPrivateKey();

    return Jwts.builder()
        .setSubject(username)
        .setIssuer("myapp")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
        .signWith(privateKey, SignatureAlgorithm.RS256)
        .compact();
  }
}
