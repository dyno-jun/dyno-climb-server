package com.dyno.climb.shared.error;

public class UserAlreadyExistsError extends RuntimeException {

  public UserAlreadyExistsError(String message) {
    super(message);
  }
}
