package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.mission.domain.Mission;
import com.dyno.climb.mission.domain.vo.UserMission;
import jakarta.annotation.Nullable;

public class MissionParticipationDto {
  private MissionDto mission;
  @Nullable private UserMissionDto userMission;

  public MissionParticipationDto() {}

  public MissionParticipationDto(MissionDto mission, UserMissionDto userMission) {
    this.mission = mission;
    this.userMission = userMission;
  }

  public static MissionParticipationDto of(
      Mission mission, BoulderProblem boulderProblem, UserMission userMission) {
    return new MissionParticipationDto(
        MissionDto.of(mission, boulderProblem), UserMissionDto.of(userMission));
  }

  public static MissionParticipationDto create() {
    return new MissionParticipationDto(MissionDto.create(), UserMissionDto.create());
  }

  public MissionDto getMission() {
    return mission;
  }

  public UserMissionDto getUserMission() {
    return userMission;
  }
}
