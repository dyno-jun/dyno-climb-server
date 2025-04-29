package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;

public class MissionGroupDto {
  private String id;
  private GymDto gym;
  private String title;
  private String description;
  private String image;
  private String type;

  private MissionGroupPeriodDto period;
  private int totalExp;

  public MissionGroupDto() {}

  public MissionGroupDto(
      String id,
      GymDto gym,
      String title,
      String description,
      String image,
      String type,
      MissionGroupPeriodDto period,
      int totalExp) {
    this.id = id;
    this.gym = gym;
    this.title = title;
    this.description = description;
    this.image = image;
    this.type = type;
    this.period = period;
    this.totalExp = totalExp;
  }

  public static MissionGroupDto of(GymMissionGroupDto gymMissionGroupDto) {
    return new MissionGroupDto(
        gymMissionGroupDto.missionGroup().getMissionGroup().getId(),
        GymDto.of(gymMissionGroupDto.gym()),
        gymMissionGroupDto.missionGroup().getMissionGroup().getTitle(),
        gymMissionGroupDto.missionGroup().getMissionGroup().getDescription(),
        gymMissionGroupDto.missionGroup().getMissionGroup().getImage().url(),
        gymMissionGroupDto.missionGroup().getMissionGroup().getType().name(),
        new MissionGroupPeriodDto(
            gymMissionGroupDto.missionGroup().getMissionGroup().getMissionPeriod().start(),
            gymMissionGroupDto.missionGroup().getMissionGroup().getMissionPeriod().end()),
        gymMissionGroupDto.missionGroup().getMissionGroup().getTotalExp());
  }

  public MissionGroupPeriodDto getPeriod() {
    return period;
  }

  public String getId() {
    return id;
  }

  public GymDto getGym() {
    return gym;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getImage() {
    return image;
  }

  public String getType() {
    return type;
  }

  public int getTotalExp() {
    return totalExp;
  }
}
