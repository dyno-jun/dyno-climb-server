package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class MissionPeriodDto {
  @Column(name = "start_at")
  private LocalDateTime startAt;

  @Column(name = "end_at")
  private LocalDateTime endAt;

  public MissionPeriodDto() {}

  public MissionPeriodDto(LocalDateTime startAt, LocalDateTime endAt) {
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
