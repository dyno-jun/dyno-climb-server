package com.dyno.climb.user.adapter.in.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UpdateUserDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Nullable
  LocalDate startDate;

  @Nullable Integer reach;

  @NotEmpty(message = "nickName cannot be empty")
  private String nickName;

  public UpdateUserDto(LocalDate startDate, Integer reach, String nickName) {
    this.startDate = startDate;
    this.reach = reach;
    this.nickName = nickName;
  }

  public UpdateUserDto() {}

  public LocalDate getStartDate() {
    return startDate;
  }

  public Integer getReach() {
    return reach;
  }

  public String getNickName() {
    return nickName;
  }
}
