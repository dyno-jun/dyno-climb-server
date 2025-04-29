package com.dyno.climb.shared.error;

public class BadRequestError extends RuntimeException {
  public BadRequestError(String message) {
    super(message);
  }
}
