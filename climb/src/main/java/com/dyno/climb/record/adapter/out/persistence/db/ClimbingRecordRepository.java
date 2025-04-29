package com.dyno.climb.record.adapter.out.persistence.db;

import com.dyno.climb.record.adapter.out.persistence.QueryClimbingRecordRepository;
import com.dyno.climb.record.adapter.out.persistence.db.entity.ClimbingRecordEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimbingRecordRepository
    extends JpaRepository<ClimbingRecordEntity, Long>, QueryClimbingRecordRepository {
  List<ClimbingRecordEntity> findByUserIdAndIsDeletedFalse(String userId);

  ClimbingRecordEntity findByUidAndUserIdAndIsDeletedFalse(String uid, String userId);

  Optional<ClimbingRecordEntity> findByUidAndIsDeletedFalse(String uid);
}
