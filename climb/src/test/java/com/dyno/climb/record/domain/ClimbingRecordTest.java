package com.dyno.climb.record.domain;

import static com.dyno.climb.mission.domain.MissionGroupParticipationTest.getMissionGroup;
import static org.junit.jupiter.api.Assertions.*;

import com.dyno.climb.mission.domain.MissionGroup;
import com.dyno.climb.shared.vo.Image;
import com.dyno.climb.shared.vo.Video;
import java.time.LocalDateTime;
import java.util.List;

public class ClimbingRecordTest {

  public static ClimbingRecord create() {
    MissionGroup missionGroup = getMissionGroup("missionGroupId");

    return new ClimbingRecord(
        "id",
        "userId",
        "gymId",
        LocalDateTime.now(),
        "boulderProblemId",
        1,
        true,
        List.of(new Video("url")),
        List.of(new Image("url")),
        10,
        List.of("tag"),
        "memo",
        true,
        false,
        MissionClimbingRecord.create(missionGroup, missionGroup.getMission("missionId")));
  }
}
