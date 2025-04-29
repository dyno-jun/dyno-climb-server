package com.dyno.climb.feature.home.dto;

import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.mission.adapter.in.web.dto.MissionGroupParticipationDto;
import java.util.List;

public class GymMissionGroupsDto {
  private GymDto gym;
  private List<MissionGroupParticipationDto> missionGroups;

  public GymMissionGroupsDto() {}

  public GymMissionGroupsDto(GymDto gym, List<MissionGroupParticipationDto> missionGroups) {
    this.gym = gym;
    this.missionGroups = missionGroups;
  }

  public static GymMissionGroupsDto create() {
    return new GymMissionGroupsDto(
        GymDto.create("gymId"), List.of(MissionGroupParticipationDto.create("id")));
  }

  public GymDto getGym() {
    return gym;
  }

  public List<MissionGroupParticipationDto> getMissionGroups() {
    return missionGroups;
  }
}
