package com.dyno.climb.shared.error;

public class UserNotExistsError extends RuntimeException {

  public UserNotExistsError(String message) {
    super(message);
  }
}
