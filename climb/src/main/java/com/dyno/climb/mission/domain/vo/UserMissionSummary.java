package com.dyno.climb.mission.domain.vo;

import com.dyno.climb.mission.domain.Mission;
import java.util.List;

public class UserMissionSummary {
  private int totalCnt;
  private int completedCnt;
  private boolean isCompleted;

  public UserMissionSummary() {}

  public UserMissionSummary(int totalCnt, int completedCnt, boolean isCompleted) {
    this.totalCnt = totalCnt;
    this.completedCnt = completedCnt;
    this.isCompleted = isCompleted;
  }

  public static UserMissionSummary of(List<Mission> missions, List<UserMission> userMissions) {
    int totalSize = missions.size();
    int completeSize = userMissions.stream().filter(UserMission::isComplete).toList().size();

    return new UserMissionSummary(totalSize, completeSize, totalSize == completeSize);
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
