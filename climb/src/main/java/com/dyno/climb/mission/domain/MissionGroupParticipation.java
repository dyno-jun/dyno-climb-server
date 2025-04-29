package com.dyno.climb.mission.domain;

import com.dyno.climb.mission.domain.vo.UserMission;
import com.dyno.climb.mission.domain.vo.UserMissionGroup;
import com.dyno.climb.shared.error.BadRequestError;
import jakarta.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionGroupParticipation {
  private MissionGroup missionGroup;
  @Nullable private UserMissionGroup userMissionGroup;
  @Nullable private List<UserMission> userMissions;

  public void filterByMissionId(String missionId) throws BadRequestError {
    missionGroup.filterByMissionId(missionId);
    if (userMissions != null) {
      userMissions =
          userMissions.stream()
              .filter(userMission -> userMission.getMissionId().equals(missionId))
              .collect(Collectors.toList());
    }
  }

  public UserMission findByMissionId(String missionId) {
    if (userMissions == null) return null;

    return userMissions.stream()
        .filter(userMission -> userMission.getMissionId().equals(missionId))
        .findFirst()
        .orElse(null);
  }
}
