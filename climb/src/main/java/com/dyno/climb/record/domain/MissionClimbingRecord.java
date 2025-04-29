package com.dyno.climb.record.domain;

import com.dyno.climb.mission.domain.Mission;
import com.dyno.climb.mission.domain.MissionGroup;
import com.dyno.climb.shared.utils.IDGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionClimbingRecord {
  private final String uid;
  private final String missionGroupId;
  private final String missionId;
  private MissionRecordStatus status;

  public static MissionClimbingRecord create(MissionGroup missionGroup, Mission mission) {
    return new MissionClimbingRecord(
        IDGenerator.generate(),
        missionGroup.getId(),
        mission.getId(),
        MissionRecordStatus.SUBMITTED);
  }

  public void setStatus(MissionRecordStatus status) {
    this.status = status;
  }
}
