package com.cog.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.PrivateKey;
import java.util.Date;

public class JwtGenerator {

  public static String generateToken(String username) throws Exception {
    PrivateKey privateKey = KeyLoader.getPrivateKey();

    return Jwts.builder()
        .setSubject(username)
        .setIssuer("myapp")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
        .signWith(privateKey, SignatureAlgorithm.RS256)
        .compact();
  }
}
