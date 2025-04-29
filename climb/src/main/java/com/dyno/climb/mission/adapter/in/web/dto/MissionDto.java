package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemDto;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.mission.domain.Mission;
import jakarta.annotation.Nullable;

public class MissionDto {
  @Nullable BoulderProblemDto boulderProblem;
  private String id;
  private String title;
  private String description;
  private String image;
  private String type;
  private int exp;

  public MissionDto() {}

  public MissionDto(
      String id,
      BoulderProblemDto boulderProblem,
      String title,
      String description,
      String image,
      String type,
      int exp) {
    this.id = id;
    this.boulderProblem = boulderProblem;
    this.title = title;
    this.description = description;
    this.image = image;
    this.type = type;
    this.exp = exp;
  }

  public static MissionDto of(Mission mission, BoulderProblem boulderProblem) {
    return new MissionDto(
        mission.getId(),
        BoulderProblemDto.of(boulderProblem),
        mission.getTitle(),
        mission.getDescription(),
        mission.getImage().url(),
        mission.getType().name(),
        mission.getExp());
  }

  public static MissionDto create() {
    return new MissionDto(
        "missionId",
        BoulderProblemDto.create(),
        "missionTitle",
        "description",
        "image",
        "type",
        100);
  }

  public String getId() {
    return id;
  }

  public BoulderProblemDto getBoulderProblem() {
    return boulderProblem;
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

  public int getExp() {
    return exp;
  }
}
