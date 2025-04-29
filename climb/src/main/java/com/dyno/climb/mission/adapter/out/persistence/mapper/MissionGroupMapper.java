package com.dyno.climb.mission.adapter.out.persistence.mapper;

import static java.util.stream.Collectors.groupingBy;

import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionConditionEntity;
import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionEntity;
import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionGroupEntity;
import com.dyno.climb.mission.domain.Mission;
import com.dyno.climb.mission.domain.MissionGroup;
import com.dyno.climb.mission.domain.vo.MissionCondition;
import com.dyno.climb.mission.domain.vo.MissionPeriod;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MissionGroupMapper {

  public List<MissionGroup> mapToGroups(
      List<MissionGroupEntity> groups,
      List<MissionEntity> missions,
      List<MissionConditionEntity> conditions) {
    final Map<String, List<MissionEntity>> missionMap =
        missions.stream().collect(groupingBy((MissionEntity::getMissionGroupId)));

    final Map<String, List<MissionConditionEntity>> conditionMap =
        conditions.stream().collect(groupingBy((MissionConditionEntity::getMissionId)));

    return groups.stream()
        .map(
            missionGroupEntity ->
                mapToGroup(
                    missionGroupEntity, missionMap.get(missionGroupEntity.getUid()), conditionMap))
        .collect(Collectors.toList());
  }

  public MissionGroup mapToGroup(
      MissionGroupEntity missionGroupEntity,
      List<MissionEntity> missions,
      Map<String, List<MissionConditionEntity>> conditionMap) {

    return new MissionGroup(
        missionGroupEntity.getUid(),
        missionGroupEntity.getGymId(),
        missionGroupEntity.getTitle(),
        missionGroupEntity.getDescription(),
        missionGroupEntity.getImage().asDomain(),
        missionGroupEntity.getType(),
        mapToMissions(missions, conditionMap),
        new MissionPeriod(
            missionGroupEntity.getPeriod().getStartAt(), missionGroupEntity.getPeriod().getEndAt()),
        missionGroupEntity.getTotalExp());
  }

  public List<Mission> mapToMissions(
      List<MissionEntity> missionEntities, Map<String, List<MissionConditionEntity>> conditionMap) {
    return missionEntities.stream()
        .map(
            missionEntity ->
                new Mission(
                    missionEntity.getUid(),
                    missionEntity.getBoulderProblemId(),
                    missionEntity.getTitle(),
                    missionEntity.getDescription(),
                    mapToConditions(conditionMap.get(missionEntity.getUid())),
                    missionEntity.getImage().asDomain(),
                    missionEntity.getType(),
                    missionEntity.getExp()))
        .collect(Collectors.toList());
  }

  public List<MissionCondition> mapToConditions(List<MissionConditionEntity> conditions) {
    return conditions.stream()
        .map(
            missionConditionEntity ->
                new MissionCondition(
                    missionConditionEntity.getType(),
                    missionConditionEntity.getInequality(),
                    missionConditionEntity.getValue()))
        .collect(Collectors.toList());
  }
}
