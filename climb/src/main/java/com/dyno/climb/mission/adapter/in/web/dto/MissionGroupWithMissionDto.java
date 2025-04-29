package com.dyno.climb.mission.adapter.in.web.dto;

import jakarta.annotation.Nullable;

public class MissionGroupWithMissionDto {
  private MissionGroupDto missionGroup;
  @Nullable private UserMissionGroupDto userMissionGroup;
  private MissionParticipationDto mission;

  public MissionGroupWithMissionDto() {}

  public MissionGroupWithMissionDto(
      MissionGroupDto missionGroup,
      UserMissionGroupDto userMissionGroup,
      MissionParticipationDto mission) {
    this.missionGroup = missionGroup;
    this.userMissionGroup = userMissionGroup;
    this.mission = mission;
  }

  public MissionGroupDto getMissionGroup() {
    return missionGroup;
  }

  public UserMissionGroupDto getUserMissionGroup() {
    return userMissionGroup;
  }

  public MissionParticipationDto getMission() {
    return mission;
  }
}
