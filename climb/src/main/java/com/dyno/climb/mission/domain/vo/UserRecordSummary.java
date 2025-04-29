package com.dyno.climb.mission.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRecordSummary {
  private int tryCnt;
  private MissionStatus status;

  public void tryMission() {
    this.tryCnt++;
  }

  public void success() {
    this.status = MissionStatus.SUCCESS;
  }

  public void fail() {
    this.status = MissionStatus.FAILURE;
  }
}
