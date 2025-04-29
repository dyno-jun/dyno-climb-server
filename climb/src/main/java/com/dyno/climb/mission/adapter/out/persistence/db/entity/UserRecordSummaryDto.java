package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import com.dyno.climb.mission.domain.vo.MissionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class UserRecordSummaryDto {
  @Column(name = "try_cnt")
  private int tryCnt;

  @Enumerated(EnumType.STRING)
  @Column(name = "mission_status")
  private MissionStatus status;

  public UserRecordSummaryDto() {}

  public UserRecordSummaryDto(int tryCnt, MissionStatus status) {
    this.tryCnt = tryCnt;
    this.status = status;
  }

  public int getTryCnt() {
    return tryCnt;
  }

  public MissionStatus getStatus() {
    return status;
  }
}
