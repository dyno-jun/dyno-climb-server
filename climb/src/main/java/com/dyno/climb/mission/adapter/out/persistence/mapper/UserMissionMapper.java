package com.dyno.climb.mission.adapter.out.persistence.mapper;

import com.dyno.climb.mission.adapter.out.persistence.db.entity.UserMissionEntity;
import com.dyno.climb.mission.domain.vo.UserMission;
import com.dyno.climb.mission.domain.vo.UserRecordSummary;
import org.springframework.stereotype.Component;

@Component
public class UserMissionMapper {

  public UserMission mapToDomain(UserMissionEntity entity) {
    return new UserMission(
        entity.getUid(),
        entity.getUserId(),
        entity.getMissionGroupId(),
        entity.getMissionId(),
        new UserRecordSummary(entity.getSummary().getTryCnt(), entity.getSummary().getStatus()));
  }
}
