package com.dyno.climb.user.adapter.in.web.dto.request;

import com.dyno.climb.user.adapter.in.web.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginUserDto {
  @Email(message = "Email should be valid")
  private String accountId;

  @ValidPassword(
      message =
          "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be between 8 and 20 characters long")
  @NotEmpty(message = "Password cannot be empty")
  private String password;

  public LoginUserDto() {}

  public LoginUserDto(String accountId, String password) {
    this.accountId = accountId;
    this.password = password;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getPassword() {
    return password;
  }
}
