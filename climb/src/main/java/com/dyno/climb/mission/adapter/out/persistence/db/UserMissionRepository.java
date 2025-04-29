package com.dyno.climb.mission.adapter.out.persistence.db;

import com.dyno.climb.mission.adapter.out.persistence.db.entity.UserMissionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMissionEntity, Long> {
  UserMissionEntity findByUid(String uid);

  List<UserMissionEntity> findByUserId(String userId);

  List<UserMissionEntity> findByUserIdAndMissionGroupId(String userId, String missionGroupId);
}
