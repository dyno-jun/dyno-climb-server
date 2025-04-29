package com.dyno.climb.mission.domain.vo;

import com.dyno.climb.mission.domain.MissionGroup;

import java.util.List;

public record UserMissionGroup(String missionGroupId, UserMissionSummary summary) {
  public static UserMissionGroup of(MissionGroup missionGroup, List<UserMission> missions) {
    return new UserMissionGroup(
        missionGroup.getId(), UserMissionSummary.of(missionGroup.getMissions(), missions));
  }
}
