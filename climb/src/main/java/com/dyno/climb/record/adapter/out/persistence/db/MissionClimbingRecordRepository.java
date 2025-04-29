package com.dyno.climb.record.adapter.out.persistence.db;

import com.dyno.climb.record.adapter.out.persistence.db.entity.MissionClimbingRecordEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionClimbingRecordRepository
    extends JpaRepository<MissionClimbingRecordEntity, Long> {
  Optional<MissionClimbingRecordEntity> findByUid(String uid);
}
