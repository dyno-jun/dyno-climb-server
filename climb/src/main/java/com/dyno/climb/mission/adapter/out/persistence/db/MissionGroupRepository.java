package com.dyno.climb.mission.adapter.out.persistence.db;

import com.dyno.climb.mission.adapter.out.persistence.db.entity.MissionGroupEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionGroupRepository extends JpaRepository<MissionGroupEntity, Long> {
  MissionGroupEntity findByUid(String id);

  List<MissionGroupEntity> findByUidIn(List<String> ids);

  List<MissionGroupEntity> findByGymIdIn(List<String> gymIds);
}
