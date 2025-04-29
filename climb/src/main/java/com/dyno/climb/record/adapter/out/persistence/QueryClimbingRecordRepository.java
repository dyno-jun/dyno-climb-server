package com.dyno.climb.record.adapter.out.persistence;

import com.dyno.climb.record.adapter.out.persistence.db.entity.ClimbingRecordEntity;
import com.dyno.climb.record.domain.command.RecordSearchQuery;
import java.util.List;

public interface QueryClimbingRecordRepository {
  List<ClimbingRecordEntity> findByQuery(RecordSearchQuery query);
}
