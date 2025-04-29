package com.dyno.climb.mission.adapter.out.persistence;

import static java.util.stream.Collectors.groupingBy;

import com.dyno.climb.mission.adapter.out.persistence.db.MissionConditionRepository;
import com.dyno.climb.mission.adapter.out.persistence.db.MissionGroupRepository;
import com.dyno.climb.mission.adapter.out.persistence.db.MissionRepository;
import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionConditionEntity;
import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionEntity;
import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionGroupEntity;
import com.dyno.climb.mission.adapter.out.persistence.mapper.MissionGroupMapper;
import com.dyno.climb.mission.application.port.MissionGroupPort;
import com.dyno.climb.mission.domain.MissionGroup;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MissionGroupPersistenceAdapter implements MissionGroupPort {
  private final MissionGroupRepository missionGroupRepository;
  private final MissionRepository missionRepository;
  private final MissionConditionRepository missionConditionRepository;
  private final MissionGroupMapper mapper;

  public MissionGroupPersistenceAdapter(
      MissionGroupRepository missionGroupRepository,
      MissionRepository missionRepository,
      MissionConditionRepository missionConditionRepository,
      MissionGroupMapper mapper) {
    this.missionGroupRepository = missionGroupRepository;
    this.missionRepository = missionRepository;
    this.missionConditionRepository = missionConditionRepository;
    this.mapper = mapper;
  }

  @Override
  public MissionGroup findById(String id) {
    final MissionGroupEntity missionGroup = missionGroupRepository.findByUid(id);
    if (missionGroup == null) return null;
    final List<MissionEntity> missions =
        missionRepository.findByMissionGroupId(missionGroup.getUid());
    final List<MissionConditionEntity> conditions =
        missionConditionRepository.findByMissionIdIn(
            missions.stream().map(MissionEntity::getUid).collect(Collectors.toList()));

    final Map<String, List<MissionConditionEntity>> conditionMap =
        conditions.stream().collect(groupingBy((MissionConditionEntity::getMissionId)));

    return mapper.mapToGroup(missionGroup, missions, conditionMap);
  }

  @Override
  public List<MissionGroup> findByIds(List<String> ids) {
    final List<MissionGroupEntity> missionGroups = missionGroupRepository.findByUidIn(ids);

    return getMissionGroups(missionGroups);
  }

  private List<MissionGroup> getMissionGroups(List<MissionGroupEntity> missionGroups) {
    final List<MissionEntity> missions =
        missionRepository.findByMissionGroupIdIn(
            missionGroups.stream().map(MissionGroupEntity::getUid).collect(Collectors.toList()));
    final List<MissionConditionEntity> conditions =
        missionConditionRepository.findByMissionIdIn(
            missions.stream().map(MissionEntity::getUid).collect(Collectors.toList()));

    return mapper.mapToGroups(missionGroups, missions, conditions);
  }

  @Override
  public List<MissionGroup> findAll() {
    final List<MissionGroupEntity> missionGroups = missionGroupRepository.findAll();
    final List<MissionEntity> missions = missionRepository.findAll();
    final List<MissionConditionEntity> conditions = missionConditionRepository.findAll();

    return mapper.mapToGroups(missionGroups, missions, conditions);
  }

  @Override
  public List<MissionGroup> findByGymIds(List<String> gymIds) {
    final List<MissionGroupEntity> missionGroups = missionGroupRepository.findByGymIdIn(gymIds);
    return getMissionGroups(missionGroups);
  }
}
