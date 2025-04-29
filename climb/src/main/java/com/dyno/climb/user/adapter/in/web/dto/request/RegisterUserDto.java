package com.dyno.climb.user.adapter.in.web.dto.request;

import com.dyno.climb.user.adapter.in.web.validator.ValidPassword;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class RegisterUserDto {
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Nullable
  LocalDate startDate;

  @Nullable Integer reach;

  @Email(message = "Email should be valid")
  private String accountId;

  @ValidPassword(
      message =
          "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be between 8 and 20 characters long")
  @NotEmpty(message = "Password cannot be empty")
  private String password;

  @NotEmpty(message = "nickName cannot be empty")
  private String nickName;

  public RegisterUserDto() {}

  public RegisterUserDto(
      LocalDate startDate, Integer reach, String accountId, String password, String nickName) {
    this.startDate = startDate;
    this.reach = reach;
    this.accountId = accountId;
    this.password = password;
    this.nickName = nickName;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getPassword() {
    return password;
  }

  public String getNickName() {
    return nickName;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public Integer getReach() {
    return reach;
  }
}
