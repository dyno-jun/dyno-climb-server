package com.dyno.climb.user.adapter.in.web.dto.response;

public class UserResponse {
  private final UserDto user;

  public UserResponse(UserDto user) {
    this.user = user;
  }

  public UserDto getUser() {
    return user;
  }
}
