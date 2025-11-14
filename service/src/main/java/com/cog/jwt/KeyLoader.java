package com.cog.jwt;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class KeyLoader {

  public static PrivateKey getPrivateKey() throws Exception {
    KeyStore keyStore = KeyStore.getInstance("JKS");
    try (InputStream is = KeyLoader.class.getResourceAsStream("/mylocalcert.jks")) {
      keyStore.load(is, "changeit".toCharArray());
    }
    return (PrivateKey) keyStore.getKey("mylocalcert", "changeit".toCharArray());
  }

  public static PublicKey getPublicKey() throws Exception {
    KeyStore keyStore = KeyStore.getInstance("JKS");
    try (InputStream is = KeyLoader.class.getResourceAsStream("/mylocalcert.jks")) {
      keyStore.load(is, "changeit".toCharArray());
    }
    Certificate cert = keyStore.getCertificate("mylocalcert");
    return cert.getPublicKey();
  }
}
