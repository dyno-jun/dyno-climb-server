package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.mission.domain.vo.UserMissionSummary;

public class UserMissionSummaryDto {
  private int totalCnt;
  private int completedCnt;
  private boolean isCompleted;

  public UserMissionSummaryDto() {}

  private UserMissionSummaryDto(int totalCnt, int completedCnt, boolean isCompleted) {
    this.totalCnt = totalCnt;
    this.completedCnt = completedCnt;
    this.isCompleted = isCompleted;
  }

  public static UserMissionSummaryDto of(UserMissionSummary summary) {
    return new UserMissionSummaryDto(
        summary.getTotalCnt(), summary.getCompletedCnt(), summary.isCompleted());
  }

  public int getTotalCnt() {
    return totalCnt;
  }

  public int getCompletedCnt() {
    return completedCnt;
  }

  public boolean isCompleted() {
    return isCompleted;
  }
}
