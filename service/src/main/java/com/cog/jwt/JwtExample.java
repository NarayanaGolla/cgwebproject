package com.cog.jwt;

public class JwtExample {
  public static void main(String[] args) throws Exception {
    String token = JwtGenerator.generateToken("sankar1");
    System.out.println("Generated JWT: " + token);

    var claims = JwtValidator.validateToken(token);
    System.out.println("Validated Token Subject: " + claims.getSubject());
  }
}
