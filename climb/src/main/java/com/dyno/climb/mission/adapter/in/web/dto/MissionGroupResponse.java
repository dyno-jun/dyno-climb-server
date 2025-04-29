package com.dyno.climb.mission.adapter.in.web.dto;

import java.util.List;

public class MissionGroupResponse {
  private List<MissionGroupParticipationDto> missionGroups;

  public MissionGroupResponse() {}

  public MissionGroupResponse(List<MissionGroupParticipationDto> missionGroups) {
    this.missionGroups = missionGroups;
  }

  public List<MissionGroupParticipationDto> getMissionGroups() {
    return missionGroups;
  }
}
