package com.dyno.climb.record.application.port;

import com.dyno.climb.record.domain.ClimbingRecord;
import com.dyno.climb.record.domain.command.RecordSearchQuery;
import java.util.List;

public interface ClimbingRecordPort {
  List<ClimbingRecord> findByUserId(String userId);

  List<ClimbingRecord> findByQuery(RecordSearchQuery query);

  ClimbingRecord findByUserIdAndId(String userId, String id);

  void save(ClimbingRecord record);
}
