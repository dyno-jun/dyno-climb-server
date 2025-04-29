package com.dyno.climb.record.adapter.out.persistence;

import com.dyno.climb.record.adapter.out.persistence.db.*;
import com.dyno.climb.record.adapter.out.persistence.db.entity.*;
import com.dyno.climb.record.application.port.ClimbingRecordPort;
import com.dyno.climb.record.domain.ClimbingRecord;
import com.dyno.climb.record.domain.command.RecordSearchQuery;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClimbingRecordPersistenceAdapter implements ClimbingRecordPort {
  private final ClimbingRecordRepository climbingRecordRepository;
  private final ClimbingRecordMapper mapper;

  public ClimbingRecordPersistenceAdapter(
      ClimbingRecordRepository climbingRecordRepository, ClimbingRecordMapper mapper) {
    this.climbingRecordRepository = climbingRecordRepository;
    this.mapper = mapper;
  }

  @Override
  public List<ClimbingRecord> findByUserId(String userId) {
    List<ClimbingRecordEntity> climbingRecords =
        climbingRecordRepository.findByUserIdAndIsDeletedFalse(userId);
    return mapper.mapToClimbingRecords(climbingRecords);
  }

  @Override
  public List<ClimbingRecord> findByQuery(RecordSearchQuery query) {
    return mapper.mapToClimbingRecords(climbingRecordRepository.findByQuery(query));
  }

  @Override
  public ClimbingRecord findByUserIdAndId(String userId, String id) {
    return mapper.mapTo(climbingRecordRepository.findByUidAndUserIdAndIsDeletedFalse(id, userId));
  }

  @Override
  public void save(ClimbingRecord record) {
    Optional<ClimbingRecordEntity> entity =
        climbingRecordRepository.findByUidAndIsDeletedFalse(record.getId());
    ClimbingRecordEntity climbingRecordEntity = null;
    if (entity.isEmpty()) {
      climbingRecordEntity = mapper.mapTo(0L, record);
    } else {
      climbingRecordEntity = mapper.mapTo(entity.get().getId(), record);
    }

    climbingRecordRepository.save(climbingRecordEntity);
  }
}
