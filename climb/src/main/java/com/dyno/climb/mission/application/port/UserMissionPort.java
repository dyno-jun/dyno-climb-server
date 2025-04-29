package com.dyno.climb.mission.application.port;

import com.dyno.climb.mission.domain.vo.UserMission;

import java.util.List;

public interface UserMissionPort {
  void save(UserMission userMission);

  List<UserMission> findByUserId(String userId);

  List<UserMission> findByUserIdMissionGroupId(String userId, String missionGroupId);
}
