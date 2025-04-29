package com.dyno.climb.mission.domain;

import com.dyno.climb.mission.domain.vo.MissionGroupType;
import com.dyno.climb.mission.domain.vo.MissionPeriod;
import com.dyno.climb.shared.error.BadRequestError;
import com.dyno.climb.shared.vo.Image;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionGroup {
  private final String id;
  private String gymId;
  private String title;
  private String description;
  private Image image;
  private MissionGroupType type;
  private List<Mission> missions;
  private MissionPeriod missionPeriod;
  private int totalExp;

  public void filterByMissionId(String missionId) throws BadRequestError {
    missions =
        missions.stream()
            .filter(mission -> Objects.equals(mission.getId(), missionId))
            .collect(Collectors.toList());
    if (missions.size() != 1) {
      throw new BadRequestError("mission not exists");
    }
  }

  public Mission getMission(String missionId) throws BadRequestError {
    return missions.stream()
        .filter(mission -> mission.getId().equals(missionId))
        .findFirst()
        .orElseThrow(() -> new BadRequestError("mission not exists"));
  }
}
