package com.dyno.climb.mission.adapter.out.persistence;

import com.dyno.climb.mission.adapter.out.persistence.db.UserMissionRepository;
import com.dyno.climb.mission.adapter.out.persistence.db.entity.UserMissionEntity;
import com.dyno.climb.mission.adapter.out.persistence.db.entity.UserRecordSummaryDto;
import com.dyno.climb.mission.adapter.out.persistence.mapper.UserMissionMapper;
import com.dyno.climb.mission.application.port.UserMissionPort;
import com.dyno.climb.mission.domain.vo.UserMission;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMissionPersistenceAdapter implements UserMissionPort {
  private final UserMissionRepository userMissionRepository;
  private final UserMissionMapper mapper;

  public UserMissionPersistenceAdapter(
      UserMissionRepository userMissionRepository, UserMissionMapper mapper) {
    this.userMissionRepository = userMissionRepository;
    this.mapper = mapper;
  }

  @Override
  public void save(UserMission userMission) {
    UserMissionEntity userMissionEntity = userMissionRepository.findByUid(userMission.getId());
    if (userMissionEntity == null) {
      userMissionEntity = UserMissionEntity.of(userMission);
    } else {
      userMissionEntity.setSummary(
          new UserRecordSummaryDto(
              userMission.getSummary().getTryCnt(), userMission.getSummary().getStatus()));
    }
    userMissionRepository.save(userMissionEntity);
  }

  @Override
  public List<UserMission> findByUserId(String userId) {
    return userMissionRepository.findByUserId(userId).stream()
        .map(mapper::mapToDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserMission> findByUserIdMissionGroupId(String userId, String missionGroupId) {
    return userMissionRepository.findByUserIdAndMissionGroupId(userId, missionGroupId).stream()
        .map(mapper::mapToDomain)
        .collect(Collectors.toList());
  }
}
