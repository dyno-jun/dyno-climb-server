package com.dyno.climb.record.application.service;

import com.dyno.climb.record.domain.command.CreateRecordDto;
import com.dyno.climb.user.domain.User;

public interface CreateRecordUseCase {
  String save(User user, CreateRecordDto command);
}
