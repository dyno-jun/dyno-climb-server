package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.mission.domain.vo.MissionStatus;
import com.dyno.climb.mission.domain.vo.UserMission;
import com.dyno.climb.mission.domain.vo.UserRecordSummary;

public class UserMissionDto {
  private UserRecordSummaryDto summary;

  public UserMissionDto(UserRecordSummaryDto summary) {
    this.summary = summary;
  }

  public UserMissionDto() {}

  public static UserMissionDto of(UserMission userMission) {
    return userMission == null
        ? null
        : new UserMissionDto(UserRecordSummaryDto.of(userMission.getSummary()));
  }

  public static UserMissionDto create() {
    return new UserMissionDto(
        UserRecordSummaryDto.of(new UserRecordSummary(3, MissionStatus.SUCCESS)));
  }

  public UserRecordSummaryDto getSummary() {
    return summary;
  }
}
