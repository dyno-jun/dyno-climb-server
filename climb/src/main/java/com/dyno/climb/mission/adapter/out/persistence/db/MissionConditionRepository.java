package com.dyno.climb.mission.adapter.out.persistence.db;

import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionConditionRepository extends JpaRepository<MissionConditionEntity, Long> {
  List<MissionConditionEntity> findByMissionIdIn(List<String> missionIds);
}
