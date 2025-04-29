package com.dyno.climb.shared.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class IDGenerator {

  public static String generate() {
    return UUID.randomUUID().toString();
  }

  public static String generateSha256Hash(String input) {
    try {
      // Get an instance of the SHA-256 algorithm
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      // Convert the input string to bytes and compute the hash
      byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

      // Convert the byte array to a hexadecimal string
      StringBuilder hexString = new StringBuilder();
      for (byte b : encodedHash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error generating SHA-256 hash", e);
    }
  }
}
