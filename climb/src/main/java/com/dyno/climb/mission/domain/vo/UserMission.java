package com.dyno.climb.mission.domain.vo;

import com.dyno.climb.mission.domain.Mission;
import com.dyno.climb.mission.domain.MissionGroup;
import com.dyno.climb.shared.utils.IDGenerator;
import com.dyno.climb.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMission {

  private String id;
  private String userId;
  private String missionGroupId;
  private String missionId;
  private UserRecordSummary summary;

  public static UserMission of(User user, MissionGroup missionGroup, Mission mission) {
    return new UserMission(
        IDGenerator.generateSha256Hash(user.getId() + missionGroup.getId() + mission.getId()),
        user.getId(),
        missionGroup.getId(),
        mission.getId(),
        new UserRecordSummary(0, MissionStatus.IN_PROGRESS));
  }

  public boolean isComplete() {
    return summary.getStatus() == MissionStatus.SUCCESS;
  }
}
