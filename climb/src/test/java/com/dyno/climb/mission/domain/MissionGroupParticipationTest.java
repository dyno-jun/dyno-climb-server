package com.dyno.climb.mission.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.dyno.climb.mission.adapter.in.web.dto.*;
import com.dyno.climb.mission.domain.vo.*;
import com.dyno.climb.shared.vo.Image;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MissionGroupParticipationTest {

  public static MissionGroupParticipation create(String id) {

    UserMissionSummary missionSummary = new UserMissionSummary(1, 1, true);
    UserMissionGroup userMissionGroup = new UserMissionGroup(id, missionSummary);

    UserRecordSummary summary = new UserRecordSummary(3, MissionStatus.SUCCESS);
    UserMission userMission = new UserMission("userMissionId", "userId", id, "missionId", summary);

    MissionGroup missionGroup = getMissionGroup(id);

    return new MissionGroupParticipation(missionGroup, userMissionGroup, List.of(userMission));
  }

  private static Mission getMission() {
    return new Mission(
        "missionId",
        "boulderProblemId",
        "title",
        "description",
        List.of(new MissionCondition(ConditionType.SKILL, InEquality.EQ, 10)),
        new Image("image"),
        MissionType.TIME,
        100);
  }

  public static MissionGroup getMissionGroup(String id) {
    return new MissionGroup(
        id,
        "gymId",
        "title",
        "description",
        new Image("image"),
        MissionGroupType.TIME,
        List.of(getMission()),
        new MissionPeriod(LocalDateTime.now(), LocalDateTime.now()),
        100);
  }

  @BeforeEach
  void setUp() {}

  @Test
  void filterByMissionId() {}
}
