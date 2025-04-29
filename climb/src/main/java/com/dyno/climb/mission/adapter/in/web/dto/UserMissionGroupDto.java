package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.mission.domain.vo.UserMissionGroup;

public class UserMissionGroupDto {
  private UserMissionSummaryDto summary;

  public UserMissionGroupDto() {}

  public UserMissionGroupDto(UserMissionSummaryDto summary) {
    this.summary = summary;
  }

  public static UserMissionGroupDto of(UserMissionGroup userMissionGroup) {
    return userMissionGroup == null
        ? null
        : new UserMissionGroupDto(UserMissionSummaryDto.of(userMissionGroup.summary()));
  }

  public UserMissionSummaryDto getSummary() {
    return summary;
  }
}
