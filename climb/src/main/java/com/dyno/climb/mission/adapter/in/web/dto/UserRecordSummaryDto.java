package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.mission.domain.vo.MissionStatus;
import com.dyno.climb.mission.domain.vo.UserRecordSummary;

public class UserRecordSummaryDto {
  private final int tryCnt;
  private final MissionStatus status;

  private UserRecordSummaryDto(int tryCnt, MissionStatus status) {
    this.tryCnt = tryCnt;
    this.status = status;
  }

  public static UserRecordSummaryDto of(UserRecordSummary summary) {
    return new UserRecordSummaryDto(summary.getTryCnt(), summary.getStatus());
  }

  public int getTryCnt() {
    return tryCnt;
  }

  public MissionStatus getStatus() {
    return status;
  }
}
