package com.dyno.climb.mission.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class MissionGroupPeriodDto {
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime startAt;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime endAt;

  public MissionGroupPeriodDto(LocalDateTime startAt, LocalDateTime endAt) {
    this.startAt = startAt;
    this.endAt = endAt;
  }

  public LocalDateTime getStartAt() {
    return startAt;
  }

  public LocalDateTime getEndAt() {
    return endAt;
  }
}
