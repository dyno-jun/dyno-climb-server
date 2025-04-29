package com.dyno.climb.mission.adapter.out.persistence.db;

import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<MissionEntity, Long> {
  List<MissionEntity> findByMissionGroupId(String missionGroupId);

  List<MissionEntity> findByMissionGroupIdIn(List<String> missionGroupIds);
}
