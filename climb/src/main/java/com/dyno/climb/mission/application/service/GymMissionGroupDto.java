package com.dyno.climb.mission.application.service;

import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.mission.domain.Mission;
import com.dyno.climb.mission.domain.MissionGroupParticipation;
import java.util.List;
import java.util.Map;

public record GymMissionGroupDto(
    Gym gym, MissionGroupParticipation missionGroup, List<BoulderProblem> boulderProblems) {
  public static GymMissionGroupDto of(
      Map<String, Gym> gyms,
      Map<String, BoulderProblem> problems,
      MissionGroupParticipation missionGroup) {
    Gym gym = gyms.get(missionGroup.getMissionGroup().getGymId());
    List<BoulderProblem> boulderProblems =
        missionGroup.getMissionGroup().getMissions().stream()
            .map(mission -> problems.get(mission.getBoulderProblemId()))
            .toList();

    return new GymMissionGroupDto(gym, missionGroup, boulderProblems);
  }

  public String getId() {
    return missionGroup.getMissionGroup().getId();
  }

  public BoulderProblem getBoulderProblemByMissionId(String missionId) {
    if (missionId.isEmpty()) {
      return null;
    }
    Mission mission = missionGroup.getMissionGroup().getMission(missionId);

    return boulderProblems.stream()
        .filter(bp -> bp.getId().equals(mission.getBoulderProblemId()))
        .findFirst()
        .orElse(null);
  }
}
