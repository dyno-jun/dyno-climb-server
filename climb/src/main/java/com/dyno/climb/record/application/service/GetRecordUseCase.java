package com.dyno.climb.record.application.service;

import com.dyno.climb.record.application.service.dto.MissionRecordDto;
import com.dyno.climb.record.domain.command.RecordSearchQuery;
import com.dyno.climb.user.domain.User;
import java.util.List;

public interface GetRecordUseCase {
  List<MissionRecordDto> getRecordsByQuery(RecordSearchQuery query);

  List<MissionRecordDto> getRecords(User user);

  MissionRecordDto getById(User user, String id);
}
