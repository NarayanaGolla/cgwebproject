package com.cog.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.PublicKey;

public class JwtValidator {

  public static Claims validateToken(String token) throws Exception {
    PublicKey publicKey = KeyLoader.getPublicKey();

    return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();
  }
}
