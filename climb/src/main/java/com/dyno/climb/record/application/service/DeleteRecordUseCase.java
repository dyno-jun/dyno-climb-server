package com.dyno.climb.record.application.service;

import com.dyno.climb.user.domain.User;

public interface DeleteRecordUseCase {
  void delete(User user, String id);
}
